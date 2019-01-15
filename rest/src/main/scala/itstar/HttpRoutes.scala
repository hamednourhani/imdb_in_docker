package itstar
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging

object HttpRoutes extends LazyLogging {

  val routes: Route = get {
    complete("")
  }
}
