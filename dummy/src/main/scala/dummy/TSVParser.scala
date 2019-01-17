package dummy

import com.typesafe.scalalogging.LazyLogging
import common.models._

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

    def toOptionalLong: Option[Long] =
      if (s.trim == """\N""") None
      else Try(s.toLong).toOption

    def toOptionalInt: Option[Int] =
      if (s.trim == """\N""") None
      else Try(s.toInt).toOption

    def toOptionalBoolean: Option[Boolean] =
      if (s.trim == """\N""") None
      else Try(s.toBoolean).toOption

    def toBinaryBoolean: Boolean =
      if (s.trim == """\N""") false
      else if (s.trim == "0") false
      else true

    def toOptionalBinaryBoolean: Option[Boolean] =
      if (s.trim == """\N""") None
      else if (s.trim == "0") Some(false)
      else Some(true)

  }

  implicit val titleCrewParser: TSVParser[String, TitleCrew] =
    new TSVParser[String, TitleCrew] {
      def read(s: String): TitleCrew =
        s.split("\t").toList match {
          case List(tconst, directors, writers) =>
            TitleCrew(tconst, directors.toOptionalList, writers.toOptionalList)
        }
    }

  implicit val titleAkaParser: TSVParser[String, TitleAka] =
    new TSVParser[String, TitleAka] {
      def read(s: String): TitleAka =
        s.split("\t").toList match {
          case List(titleId, ordering, title, region, language, types, attributes, isOriginalTitle) =>
            TitleAka(
              titleId,
              ordering.toInt,
              title,
              region.toOptionalString,
              language.toOptionalString,
              types.toOptionalList,
              attributes.toOptionalList,
              isOriginalTitle.toBinaryBoolean
            )
        }
    }

  implicit val titleBasicParser: TSVParser[String, TitleBasic] =
    new TSVParser[String, TitleBasic] {
      def read(s: String): TitleBasic =
        s.split("\t").toList match {
          case List(tconst,
                    titleType,
                    primaryTitle,
                    originalTitle,
                    isAdult,
                    startYear,
                    endYear,
                    runtimeMinutes,
                    genres) =>
            TitleBasic(
              tconst,
              titleType,
              primaryTitle,
              originalTitle,
              isAdult.toBinaryBoolean,
              startYear,
              endYear.toOptionalString,
              runtimeMinutes.toOptionalString,
              genres.toOptionalList
            )
        }
    }

  implicit val nameBasicParser: TSVParser[String, NameBasic] =
    new TSVParser[String, NameBasic] {
      def read(s: String): NameBasic =
        s.split("\t").toList match {
          case List(nconst, primaryName, birthYear, deathYear, primaryProfession, knownForTitles) =>
            NameBasic(
              nconst,
              primaryName,
              birthYear,
              deathYear.toOptionalString,
              primaryProfession.toOptionalList,
              knownForTitles.toOptionalList
            )
        }
    }

  implicit val titleEpisodeParser: TSVParser[String, TitleEpisode] =
    new TSVParser[String, TitleEpisode] {
      def read(s: String): TitleEpisode =
        s.split("\t").toList match {
          case List(tconst, parentTconst, seasonNumber, episodeNumber) =>
            TitleEpisode(
              tconst,
              parentTconst,
              seasonNumber.toOptionalInt,
              episodeNumber.toOptionalInt
            )
        }
    }

  implicit val titlePrincipalParser: TSVParser[String, TitlePrincipal] =
    new TSVParser[String, TitlePrincipal] {
      def read(s: String): TitlePrincipal =
        s.split("\t").toList match {
          case List(tconst, ordering, nconst, category, job, characters) =>
            TitlePrincipal(
              tconst,
              ordering.toInt,
              nconst,
              category,
              job.toOptionalString,
              characters.toOptionalString
            )
        }
    }

  implicit val titleRatingParser: TSVParser[String, TitleRating] =
    new TSVParser[String, TitleRating] {
      def read(s: String): TitleRating =
        s.split("\t").toList match {
          case List(tconst, averageRating, numVotes) =>
            TitleRating(
              tconst,
              averageRating.toDouble,
              numVotes.toInt
            )
        }
    }

  implicit class StringSyntax[L <: String](val s: L) extends AnyVal {
    def parseTo[T](implicit parser: TSVParser[L, T]): T =
      parser.read(s)
  }

}
