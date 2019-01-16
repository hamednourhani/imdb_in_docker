package dummy

import com.typesafe.scalalogging.LazyLogging
import common.models.TitleCrew

import scala.util.Try

trait TSVParser[U, T] {
  def read(u: U): T
}

object TSVParser extends LazyLogging {

  private[TSVParser] implicit class StringConverter(val s: String) extends AnyVal {
    def toOptionalString: Option[String] =
      if (s.trim == """\N""") None
      else Some(s)

    def toOptionalList: List[String] =
      if (s.trim == """\N""") Nil
      else s.split(",").toList

    def toOptionalLong : Option[Long] =
      if (s.trim == """\N""") None
      else Try(s.toLong).toOption

    def toOptionalBoolean : Option[Boolean] =
      if (s.trim == """\N""") None
      else Try(s.toBoolean).toOption

  }

  implicit val titleCrewParser: TSVParser[String, TitleCrew] =
    new TSVParser[String, TitleCrew] {
      def read(s: String): TitleCrew =
        s.split("\t").toList match {
          case List(tconst, directors, writers) =>
            TitleCrew(tconst, directors.toOptionalList, writers.toOptionalList)
        }
    }

  implicit class StringSyntax[L <: String](val s: L) extends AnyVal {
    def parseTo[T](implicit parser: TSVParser[L, T]): T =
      parser.read(s)
  }

}
