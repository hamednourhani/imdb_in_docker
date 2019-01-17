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
    def primaryName       = column[String]("primaryname")
    def birthYear         = column[String]("birthyear")
    def deathYear         = column[Option[String]]("deathyear", O.Default(null))
    def primaryProfession = column[List[String]]("primaryprofession", O.Default(Nil))
    def knownForTitles    = column[List[String]]("knownforTitles", O.Default(Nil))

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
