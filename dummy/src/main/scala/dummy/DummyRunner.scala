package dummy
import com.typesafe.scalalogging.LazyLogging
import common.config.ConfigHolder._
import common.models._
import common.repos._

import scala.concurrent.Future
import scala.util.{Failure, Success}

object DummyRunner extends App with LazyLogging {

  val dummyFiles = config.getConfig("dummyFiles")

  val nameBasicsFile      = dummyFiles.getString("nameBasics")
  val titleAkasFile       = dummyFiles.getString("titleAkas")
  val titleBasicsFile     = dummyFiles.getString("titleBasics")
  val titleCrewsFile      = dummyFiles.getString("titleCrews")
  val titleEpisodesFile   = dummyFiles.getString("titleEpisodes")
  val titlePrincipalsFile = dummyFiles.getString("titlePrincipals")
  val titleRatingsFile    = dummyFiles.getString("titleRatings")

  logger.info("importing data to tables ...")

  import TSVParser._

  val importList = List(
    DummyImporter.importFile[NameBasic](nameBasicsFile, NameBasicRepoImpl.batchInsert),
    DummyImporter.importFile[TitleBasic](titleBasicsFile, TitleBasicRepoImpl.batchInsert),
    DummyImporter.importFile[TitleAka](titleAkasFile, TitleAkaRepoImpl.batchInsert),
    DummyImporter.importFile[TitleCrew](titleCrewsFile, TitleCrewRepoImpl.batchInsert),
    DummyImporter.importFile[TitleEpisode](titleEpisodesFile, TitleEpisodeRepoImpl.batchInsert),
    DummyImporter.importFile[TitlePrincipal](titlePrincipalsFile, TitlePrincipalRepoImpl.batchInsert),
    DummyImporter.importFile[TitleRating](titleRatingsFile, TitleRatingRepoImpl.batchInsert)
  )

  Future
    .sequence(importList)
    .onComplete {
      case Success(_) =>
        logger.info("import dummy data succeeded")
        system.terminate()

      case Failure(e) =>
        logger.error("error while import dummy data", e)
        system.terminate()
    }

}
