package common.repos

import common.config.ConfigHolder.ec
import common.database.DatabaseConnectorImpl.db
import common.database.ExtendedPostgresProfile.api._
import common.models.NameBasic
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait NameBasicRepo {
  def batchInsert(ts: Seq[NameBasic]): Future[Int]
}

object NameBasicRepoImpl extends NameBasicRepo with NameBasicComponent {

  def batchInsert(ts: Seq[NameBasic]): Future[Int] = {
    val action = nameBasicsTable ++= ts
    db.run(action).map(_.size)
  }
}

trait NameBasicComponent {

  final class NameBasicTable(tag: Tag) extends Table[NameBasic](tag, "name_basics") {

    def nconst            = column[String]("nconst", O.PrimaryKey)
    def primaryName       = column[String]("primaryName")
    def birthYear         = column[String]("birthYear")
    def deathYear         = column[Option[String]]("deathYear", O.Default(null))
    def primaryProfession = column[List[String]]("primaryProfession", O.Default(Nil))
    def knownForTitles    = column[List[String]]("knownForTitles", O.Default(Nil))

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
