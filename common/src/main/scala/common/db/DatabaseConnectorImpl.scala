package common.db

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.json.PgJsonExtensions
import com.typesafe.scalalogging.LazyLogging

object DatabaseConnectorImpl extends PostgresProfiler {

  import api._

  val db: backend.DatabaseDef = Database.forConfig("db")
  db.createSession()

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
    with PgSprayJsonSupport
    with PgEnumSupport {

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

object PostgresProfiler extends PostgresProfiler with PgJsonExtensions
