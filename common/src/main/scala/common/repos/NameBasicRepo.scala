package common.repos

import common.models.NameBasic
import slick.lifted.ProvenShape
import common.database.ExtendedPostgresProfile.api._

trait NameBasicRepo {}

object NameBasicRepoImpl extends NameBasicRepo with NameBasicComponent {
  def log() =
  println(nameBasicsTable.result.headOption)
}

trait NameBasicComponent {


  final class NameBasicTable(tag: Tag) extends Table[NameBasic](tag, "name_basics") {

    def nconst            = column[String]("nconst", O.PrimaryKey)
    def primaryName       = column[String]("primaryName")
    def birthYear         = column[String]("birthYear")
    def deathYear         = column[Option[String]]("deathYear", O.Default(null))
    def primaryProfession = column[String]("primaryProfession")
    def knownForTitles    = column[String]("knownForTitles")

    def * : ProvenShape[NameBasic] =
      (
        nconst,
        primaryName,
        birthYear,
        deathYear,
        primaryProfession,
        knownForTitles
      ) <> ((NameBasic.apply _).tupled, NameBasic.unapply)
  }

  val nameBasicsTable = TableQuery[NameBasicTable]



}
