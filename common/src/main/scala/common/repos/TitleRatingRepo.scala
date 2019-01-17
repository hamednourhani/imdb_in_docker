package common.repos

import common.models.TitleRating
import slick.lifted.ProvenShape
import common.database.ExtendedPostgresProfile.api._

trait TitleRatingRepo {}

object TitleRatingRepoImpl extends TitleRatingRepo with TitleRatingComponent {}

trait TitleRatingComponent {


  final class TitleRatingTable(tag: Tag)
      extends Table[TitleRating](tag, "title_ratings") {

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
