package common.repos

import common.config.ConfigHolder.ec
import common.database.DatabaseConnectorImpl.db
import common.database.ExtendedPostgresProfile.api._
import common.models.TitleBasic
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait TitleBasicRepo {
  def batchInsert(ts: Seq[TitleBasic]): Future[Int]
}

object TitleBasicRepoImpl extends TitleBasicRepo with TitleBasicComponent {

  def batchInsert(ts: Seq[TitleBasic]): Future[Int] = {
    val action = titleBasicsTable ++= ts
    db.run(action).map(_.size)
  }
}

trait TitleBasicComponent {

  final class TitleBasicTable(tag: Tag) extends Table[TitleBasic](tag, "title_basics") {

    def tconst         = column[String]("tconst")
    def titleType      = column[String]("titleType")
    def primaryTitle   = column[String]("primaryTitle")
    def originalTitle  = column[String]("originalTitle")
    def isAdult        = column[Boolean]("isAdult", O.Default(false))
    def startYear      = column[String]("startYear")
    def endYear        = column[Option[String]]("endYear", O.Default(null))
    def runtimeMinutes = column[Option[String]]("runtimeMinutes", O.Default(null))
    def genres         = column[List[String]]("genres", O.Default(Nil))

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
