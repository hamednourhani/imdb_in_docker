package itstar.controllers
import itstar.repos.{Repos, ReposComponent, ReposImpl}

object TitleController {

  private val singleton = new TitleControllerImpl(ReposImpl)

  def apply(): TitleControllerImpl = singleton

}

class TitleControllerImpl(val repos: Repos) extends ReposComponent{



}




