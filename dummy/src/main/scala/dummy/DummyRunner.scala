package dummy
import java.nio.file.Paths

import akka.stream.scaladsl.{FileIO, Flow, Framing, Sink}
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import common.models.TitleCrew
import common.repos.TitleCrewRepoImpl
import scala.concurrent.duration._
import common.config.ConfigHolder._

import scala.util.{Failure, Success}

object DummyRunner extends App with LazyLogging {

  import TSVParser._

  val fileName = "title.crews.tsv"

  val parserFlow = Flow[String].map(_.parseTo[TitleCrew])

  val titleCrewImportStream =FileIO
    .fromPath(Paths.get(getClass.getClassLoader.getResource(fileName).toURI))
    .via(Framing.delimiter(ByteString("\n"), maximumFrameLength = 10000000, allowTruncation = true))
    .drop(1)
    .map(_.utf8String)
    .via(parserFlow)
    .groupedWithin(100,10.second)
    .mapAsync(3)(TitleCrewRepoImpl.batchInsert)
    .reduce(_ + _)
    .runWith(Sink.foreach(count => logger.info(s"$count * 100 of TitleCrew inserted in db")))

  titleCrewImportStream
      .andThen{
        case _ => system.terminate
      }.onComplete{
      case Success(value) =>
        logger.info(value.toString)
      case Failure(e) =>
      logger.error("error while importing title crew to table",e)
  }







}
