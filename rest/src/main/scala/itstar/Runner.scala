package itstar
import akka.http.scaladsl.Http
import com.typesafe.scalalogging.LazyLogging
import itstar.db.DatabaseConnectorImpl

object Runner extends App with LazyLogging {

  import itstar.utils.ConfigHolder._

  sys.addShutdownHook(system.terminate())
  sys.addShutdownHook(DatabaseConnectorImpl.closeDB())

  logger.info(s"starting http server on $host:$port :")
  val routes  = HttpRoutes.routes
  val bindFut = Http().bindAndHandle(routes, host, port)

  bindFut.flatMap(_.unbind())
}


/**
  * Requirement #1 (easy):
  *
  * IMDb copycat: Present the user with endpoint for allowing them to search by movie’s primary title or original title. The outcome should be related information to that title, including cast and crew.
  *
  * Requirement #2 (easy):
  *
  * Top rated movies: Given a query by the user, you must provide what are the top rated movies for a genre (If the user searches horror, then it should show a list of top rated horror movies).
  *
  * Requirement #3 (easy):
  *
  * Typecasting: Given a query by the user, where he/she provides an actor/actress name, the system should determine if that person has become typecasted (at least half of their work is one genre).
  *
  * Requirement #4 (easy):
  *
  * Find the coincidence: Given a query by the user, where the input is two actors/actresses names, the application replies with a list of movies or TV shows that both people have shared.
  *
  * Requirement #5 (difficult):
  *
  * Six degrees of Kevin Bacon: Given a query by the user, you must provide what’s the degree of separation between the person (e.g. actor or actress) the user has entered and Kevin Bacon
*/