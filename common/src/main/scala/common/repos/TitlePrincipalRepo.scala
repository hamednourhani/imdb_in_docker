package common.repos

import common.models.TitlePrincipal
import slick.lifted.ProvenShape
import common.database.ExtendedPostgresProfile.api._

trait TitlePrincipalRepo {}

object TitlePrincipalRepoImpl extends TitlePrincipalRepo with TitlePrincipalComponent {}

trait TitlePrincipalComponent {


  final class TitlePrincipalTable(tag: Tag)
      extends Table[TitlePrincipal](tag, "title_principals") {

    def tconst     = column[String]("tconst")
    def ordering   = column[Int]("ordering")
    def nconst     = column[String]("nconst")
    def category   = column[String]("category")
    def job        = column[Option[String]]("job",O.Default(null))
    def characters = column[Option[String]]("characters",O.Default(null))

    def * : ProvenShape[TitlePrincipal] =
      (
        tconst,
        ordering,
        nconst,
        category,
        job,
        characters
      ) <> ((TitlePrincipal.apply _).tupled, TitlePrincipal.unapply)
  }

  protected val titlePrincipalsTable = TableQuery[TitlePrincipalTable]

}
