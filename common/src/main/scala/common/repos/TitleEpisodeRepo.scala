package common.repos

import common.config.ConfigHolder.ec
import common.database.DatabaseConnectorImpl.db
import common.database.ExtendedPostgresProfile.api._
import common.models.TitleEpisode
import slick.lifted.ProvenShape

import scala.concurrent.Future
trait TitleEpisodeRepo {}

object TitleEpisodeRepoImpl extends TitleEpisodeRepo with TitleEpisodeComponent {
  def batchInsert(ts: Seq[TitleEpisode]): Future[Int] = {
    val action = titleEpisodesTable ++= ts
    db.run(action).map(_.size)
  }
}

trait TitleEpisodeComponent {

  final class TitleEpisodeTable(tag: Tag) extends Table[TitleEpisode](tag, "title_episodes") {

    def tconst        = column[String]("tconst")
    def parentTconst  = column[String]("parentTconst")
    def seasonNumber  = column[Option[Int]]("seasonNumber", O.Default(null))
    def episodeNumber = column[Option[Int]]("episodeNumber", O.Default(null))

    def * : ProvenShape[TitleEpisode] =
      (
        tconst,
        parentTconst,
        seasonNumber,
        episodeNumber
      ) <> ((TitleEpisode.apply _).tupled, TitleEpisode.unapply)
  }

  protected val titleEpisodesTable = TableQuery[TitleEpisodeTable]

}
