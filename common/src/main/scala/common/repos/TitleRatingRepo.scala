package common.repos

import common.config.ConfigHolder.ec
import common.database.DatabaseConnectorImpl.db
import common.database.ExtendedPostgresProfile.api._
import common.models.TitleRating
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait TitleRatingRepo {}

object TitleRatingRepoImpl extends TitleRatingRepo with TitleRatingComponent {
  def batchInsert(ts: Seq[TitleRating]): Future[Int] = {
    val action = titleRatingsTable ++= ts
    db.run(action).map(_.size)
  }
}

trait TitleRatingComponent {

  final class TitleRatingTable(tag: Tag) extends Table[TitleRating](tag, "title_ratings") {

    def tconst        = column[String]("tconst")
    def averageRating = column[Double]("averageRating")
    def numVotes      = column[Int]("numVotes")

    def * : ProvenShape[TitleRating] =
      (
        tconst,
        averageRating,
        numVotes,
      ) <> ((TitleRating.apply _).tupled, TitleRating.unapply)
  }

  protected val titleRatingsTable = TableQuery[TitleRatingTable]

}
