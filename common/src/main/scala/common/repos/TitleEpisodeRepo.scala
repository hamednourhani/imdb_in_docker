package common.repos

import common.models.TitleEpisode
import slick.lifted.ProvenShape
import common.database.ExtendedPostgresProfile.api._
trait TitleEpisodeRepo {}

object TitleEpisodeRepoImpl extends TitleEpisodeRepo with TitleEpisodeComponent {}

trait TitleEpisodeComponent {

  final class TitleEpisodeTable(tag: Tag)
      extends Table[TitleEpisode](tag, "title_episodes") {

    def tconst        = column[String]("tconst")
    def parentTconst  = column[String]("parentTconst")
    def seasonNumber  = column[Option[Int]]("seasonNumber",O.Default(null))
    def episodeNumber = column[Option[Int]]("episodeNumber",O.Default(null))

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
