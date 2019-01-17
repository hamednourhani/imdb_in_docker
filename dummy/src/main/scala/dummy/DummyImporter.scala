package dummy
import java.nio.file.Paths

import akka.Done
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import common.config.ConfigHolder._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object DummyImporter extends LazyLogging {
  import TSVParser._

  def importFile[T](
      fileName: String,
      f: Seq[T] => Future[Int]
  )(
      implicit parser: TSVParser[String, T]
  ): Future[_] = {

    val importStreamResult: Future[Done] =
      FileIO
        .fromPath(Paths.get(getClass.getClassLoader.getResource(fileName).toURI))
        .via(Framing.delimiter(ByteString("\n"), maximumFrameLength = 10000000, allowTruncation = true))
        .drop(1)
        .map(_.utf8String)
        .map(_.parseTo[T])
        .groupedWithin(100, 10.second)
        .mapAsync(3)(f)
        .reduce(_ + _)
        .runWith(Sink.foreach(count => logger.info(s"$count * 100 row inserted in table")))

    importStreamResult
      .onComplete {
        case Success(value) =>
          logger.info(value.toString)
        case Failure(e) =>
          logger.error(s"error while importing rows to table", e)
      }

    importStreamResult
  }

}
