package itstar.repos

import itstar.db.DatabaseConnectorImpl.db
import itstar.db.DatabaseConnectorImpl.profile.api._
import itstar.models.TitleCrew
import itstar.utils.ConfigHolder.ec
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait TitleCrewRepo {

  def batchInsert(ts: Seq[TitleCrew]): Future[Int]

}

object TitleCrewRepoImpl extends TitleCrewRepo with TitleCrewComponent {

  def batchInsert(ts: Seq[TitleCrew]): Future[Int] = {
    val action = titleCrewsTable ++= ts
    db.run(action).map(_.size)
  }
}

trait TitleCrewComponent {

  final class TitleCrewTable(tag: Tag) extends Table[TitleCrew](tag, "title_crews") {

    def tconst    = column[String]("tconst")
    def directors = column[List[String]]("directors", O.Default(Nil))
    def writers   = column[List[String]]("writers", O.Default(Nil))

    def * : ProvenShape[TitleCrew] =
      (
        tconst,
        directors,
        writers,
      ) <> ((TitleCrew.apply _).tupled, TitleCrew.unapply)
  }

  protected val titleCrewsTable = TableQuery[TitleCrewTable]

}
