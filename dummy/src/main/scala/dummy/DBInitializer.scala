package dummy

import com.snapptrip.flights.DI._
import com.typesafe.scalalogging.LazyLogging
import common.repos._
import slick.jdbc.meta.MTable

import scala.concurrent.Future


object DBInitializer extends LazyLogging with
  TitleBasicComponent with
  TitleAkaComponent with
  TitleCrewComponent with
  TitleEpisodeComponent with
  TitlePrincipalComponent with
  TitleRatingComponent with
  NameBasicComponent {


  val tables = List(
    nameBasicsTable,
    titleBasicsTable,
    titleAkasTable,
    titleCrewsTable,
    titleEpisodesTable,
    titlePrincipalsTable,
    titleRatingsTable
  )

  def initDbs(): Future[List[Unit]] = {
    logger.info(s"initiating dbs on : ${config.getString("db.url")}")
    val existing = db.run(MTable.getTables)
    val f = existing.flatMap(v => {
      val names = v.map(mt => mt.name.name)
      val createIfNotExist =
        tables.filter(table => !names.contains(table.baseTableRow.tableName))
          .map(_.schema.create)
      db.run(DBIO.sequence(createIfNotExist))
    })
    f
  }
}