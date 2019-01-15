package itstar.controllers
import itstar.repos.{Repos, ReposComponent, ReposImpl}

object TitleController {

  private val singleton = new TitleController(ReposImpl)

  def apply(): TitleController = singleton

}

private class TitleController(val repos: Repos) extends ReposComponent{



}




