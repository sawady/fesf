package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.link.BookmarkablePageLink

@SerialVersionUID(1L)
class UpsPage extends WebPage with Serializable {

  this.initialize()
  private def initialize() = {
    this.add(new BookmarkablePageLink("homeLink", getApplication().getHomePage()))
  }
}