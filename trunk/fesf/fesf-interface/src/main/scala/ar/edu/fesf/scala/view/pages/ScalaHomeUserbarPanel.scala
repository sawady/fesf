package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.wicket.application.SecuritySession

class ScalaHomeUserbarPanel(id: String,
  f_homeLink: String => AjaxFallbackLink[_],
  f_bookSearchPanel: String => Panel,
  f_loggedInPanel: String => Panel) extends Panel(id) {

  val AUTHENTICATION_PANEL = "authentication"

  this.initialize()

  def initialize() = {
    this.add(f_homeLink("homeLink"),
      f_homeLink("titleLink"),
      f_bookSearchPanel("searchbar"))

    if (this.getSession().asInstanceOf[SecuritySession].signedIn()) {
      this.add(f_loggedInPanel(AUTHENTICATION_PANEL))
    } else {
      this.add(new ScalaLoggedOutUserbarPanel(AUTHENTICATION_PANEL))
    }

  }

}