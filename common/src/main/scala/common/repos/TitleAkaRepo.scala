package common.repos
import common.config.ConfigHolder.ec
import common.database.DatabaseConnectorImpl.db
import common.database.ExtendedPostgresProfile.api._
import common.models.TitleAka
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait TitleAkaRepo {
  def batchInsert(ts: Seq[TitleAka]): Future[Int]
}

object TitleAkaRepoImpl extends TitleAkaRepo with TitleAkaComponent {
  def batchInsert(ts: Seq[TitleAka]): Future[Int] = {
    val action = titleAkasTable ++= ts
    db.run(action).map(_.size)
  }
}

trait TitleAkaComponent {

  final class TitleAkaTable(tag: Tag) extends Table[TitleAka](tag, "title_basics") {

    def titleId         = column[String]("titleId")
    def ordering        = column[Int]("ordering")
    def title           = column[String]("title")
    def region          = column[Option[String]]("region", O.Default(null))
    def language        = column[Option[String]]("language", O.Default(null))
    def types           = column[List[String]]("types", O.Default(Nil))
    def attributes      = column[List[String]]("attributes", O.Default(Nil))
    def isOriginalTitle = column[Boolean]("isOriginalTitle")

    def * : ProvenShape[TitleAka] =
      (
        titleId,
        ordering,
        title,
        region,
        language,
        types,
        attributes,
        isOriginalTitle
      ) <> ((TitleAka.apply _).tupled, TitleAka.unapply)
  }

  protected val titleAkasTable = TableQuery[TitleAkaTable]

}
