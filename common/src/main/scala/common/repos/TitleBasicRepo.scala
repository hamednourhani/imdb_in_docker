package common.repos

import common.models.TitleBasic
import slick.lifted.ProvenShape
import slick.jdbc.PostgresProfile.api._


trait TitleBasicRepo {}

object TitleBasicRepoImpl extends TitleBasicRepo with TitleBasicComponent {}

trait TitleBasicComponent {

  private[TitleBasicComponent] final class TitleBasicTable(tag: Tag) extends Table[TitleBasic](tag, "title_basics") {

    def tconst         = column[String]("tconst")
    def titleType      = column[String]("titleType")
    def primaryTitle   = column[String]("primaryTitle")
    def originalTitle  = column[String]("originalTitle")
    def isAdult        = column[String]("isAdult")
    def startYear      = column[String]("startYear")
    def endYear        = column[Option[String]]("endYear", O.Default(null))
    def runtimeMinutes = column[Option[String]]("runtimeMinutes", O.Default(null))
    def genres         = column[Option[String]]("genres")

    def * : ProvenShape[TitleBasic] =
      (
        tconst,
        titleType,
        primaryTitle,
        originalTitle,
        isAdult,
        startYear,
        endYear,
        runtimeMinutes,
        genres
      ) <> ((TitleBasic.apply _).tupled, TitleBasic.unapply)
  }

  protected val titleBasicsTable = TableQuery[TitleBasicTable]

}