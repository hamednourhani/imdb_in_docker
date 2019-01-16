package itstar.utils
import com.typesafe.scalalogging.LazyLogging
import itstar.models.TitleCrew
trait TSVParser[U, T] {
  def read(u: U): T
}

object TSVParser extends LazyLogging {

  private[TSVParser] implicit class StringConverter(val s: String) extends AnyVal {
    def toNullOption: Option[String] =
      if (s.trim == """\N""") {
        None
      } else {
        Some(s)
      }
  }

  implicit val titleCrewParser: TSVParser[String, TitleCrew] =
    new TSVParser[String, TitleCrew] {
      def read(s: String): TitleCrew =
        s.split("\t").toList match {
          case List(tconst, directors, writers) =>
            TitleCrew(tconst, directors.toNullOption, writers.toNullOption)
        }
    }

  implicit class StringSyntax[L <: String](val s: L) extends AnyVal {
    def parseTo[T](implicit parser: TSVParser[L, T]): T =
      parser.read(s)
  }

}
