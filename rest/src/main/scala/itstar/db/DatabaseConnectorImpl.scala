package itstar.db

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.PostgresProfile

object DatabaseConnectorImpl extends PostgresProfile with LazyLogging {

  import api._

  val db: backend.DatabaseDef = Database.forConfig("db")
  db.createSession()

  def closeDB(): Unit = {
    logger.info("closing db connections")
    db.close()
  }

}


/**
* package pintapin.utils
  *
  * import com.github.tminglei.slickpg._
  * import org.json4s._
  *
  * trait ExtendedPostgresProfile extends ExPostgresProfile
  *   with PgArraySupport
  *   with PgJson4sSupport
  *   with PgRangeSupport
  *   with PgDateSupport
  *   with PgDate2Support
  *   with PgSearchSupport
  *   with PgCirceJsonSupport
  *   with PgEnumSupport{
  *
  *   override val pgjson = "jsonb"
  *   type DOCType = org.json4s.native.Document
  *   override val jsonMethods = org.json4s.native.JsonMethods.asInstanceOf[JsonMethods[DOCType]]
  *
  *   override val api: API = new API {}
  *
  *   trait API extends super.API with ArrayImplicits
  *     with DateTimeImplicits
  *     with SimpleDateTimeImplicits
  *     with JsonImplicits
  *     with SearchImplicits
  *     with SearchAssistants
  *     with CirceImplicits
  *   with Date2DateTimeImplicitsDuration{}
  *
  *
  * }
  *
  * object ExtendedPostgresProfile extends ExtendedPostgresProfile
  */
