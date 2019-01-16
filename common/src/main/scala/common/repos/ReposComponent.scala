package common.repos

trait ReposComponent {
  def repos: Repos
}

trait Repos {
  def nameBasicRepo: NameBasicRepo
  def titleAkaRepo: TitleAkaRepo
  def titleBasicRepo: TitleBasicRepo
  def titleCrewRepo: TitleCrewRepo
  def titleEpisodeRepo: TitleEpisodeRepo
  def titlePrincipalRepo: TitlePrincipalRepo
  def titleRatingRepo: TitleRatingRepo
}

object ReposImpl extends Repos {
  val nameBasicRepo: NameBasicRepo           = NameBasicRepoImpl
  val titleAkaRepo: TitleAkaRepo             = TitleAkaRepoImpl
  val titleBasicRepo: TitleBasicRepo         = TitleBasicRepoImpl
  val titleCrewRepo: TitleCrewRepo           = TitleCrewRepoImpl
  val titleEpisodeRepo: TitleEpisodeRepo     = TitleEpisodeRepoImpl
  val titlePrincipalRepo: TitlePrincipalRepo = TitlePrincipalRepoImpl
  val titleRatingRepo: TitleRatingRepo       = TitleRatingRepoImpl
}
