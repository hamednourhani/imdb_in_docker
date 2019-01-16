package common.config

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContext

object ConfigHolder {

  lazy val config: Config   = ConfigFactory.load()
  lazy val dbConfig: Config = config.getConfig("db")

  implicit val system: ActorSystem        = ActorSystem("ActorSystem")
  implicit val materializer: Materializer = ActorMaterializer()
  implicit val ec: ExecutionContext       = system.dispatcher


}
