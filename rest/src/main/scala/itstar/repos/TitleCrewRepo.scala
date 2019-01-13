package itstar.repos

import itstar.db.DatabaseConnectorImpl
import itstar.models.TitleCrew
import slick.lifted.ProvenShape

trait TitleCrewRepo {}

object TitleCrewRepoImpl extends TitleCrewRepo with TitleCrewComponent {}

trait TitleCrewComponent {

  import DatabaseConnectorImpl.profile.api._

  private[TitleCrewComponent] final class TitleCrewTable(tag: Tag) extends Table[TitleCrew](tag, "title_crews") {

    def tconst    = column[String]("tconst")
    def directors = column[Option[String]]("directors",O.Default(null))
    def writers   = column[Option[String]]("writers",O.Default(null))

    def * : ProvenShape[TitleCrew] =
      (
        tconst,
        directors,
        writers,
      ) <> ((TitleCrew.apply _).tupled, TitleCrew.unapply)
  }

  protected val titleCrewsTable = TableQuery[TitleCrewTable]

}
