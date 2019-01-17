package dummy
import com.typesafe.scalalogging.LazyLogging
import common.config.ConfigHolder._

import scala.concurrent.Future
import scala.util.{Failure, Success}

object DummyRunner extends App with LazyLogging {

  import common.database.ExtendedPostgresProfile.api._
  import common.database.DatabaseConnectorImpl.db

  db.run(sql"".as[String]).foreach(println)

  logger.info("starting dummy runner ...")

  logger.info("initializing tables ...")
  val initResult: Future[List[Unit]] = DBInitializer.initTables()

  initResult.andThen {
    case Success(_) =>
      logger.info("tables initialized successfully")
    case Failure(e) =>
      logger.error("error while initializing tables", e)
      system.terminate()
  }

  initResult
    .flatMap { _ =>
      logger.info("importing data to tables ...")
      Future.successful()
    }
    .andThen { case _ => system.terminate() }

}
