package common.models

/**
  * tconst (string) - alphanumeric unique identifier of the title
  * directors (array of nconsts) - director(s) of the given title
  * writers (array of nconsts) – writer(s) of the given title
  */
case class TitleCrew(
    tconst: String,
    directors: List[String] = Nil,
    writers: List[String] = Nil
)
