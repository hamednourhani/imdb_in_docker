package common.database

import com.github.tminglei.slickpg._
import com.typesafe.scalalogging.LazyLogging
import ExtendedPostgresProfile.api.Database

object DatabaseConnectorImpl extends LazyLogging {

  val db: ExtendedPostgresProfile.backend.Database = connect()

  private def connect(): ExtendedPostgresProfile.backend.Database = {
    logger.info("connecting to db")
    Database.forConfig("db")
  }

  def closeDB(): Unit = {
    logger.info("closing db connections")
    db.close()
  }

}

trait PostgresProfiler
    extends ExPostgresProfile
    with PgArraySupport
    with PgRangeSupport
    with PgDateSupport
    with PgDate2Support
    with PgSearchSupport
    with PgSprayJsonSupport {

  override val pgjson = "jsonb"

  override val api: API = new API {}

  trait API
      extends super.API
      with ArrayImplicits
      with DateTimeImplicits
      with SimpleDateTimeImplicits
      with SearchImplicits
      with SearchAssistants
      with JsonImplicits

}

object ExtendedPostgresProfile extends PostgresProfiler
