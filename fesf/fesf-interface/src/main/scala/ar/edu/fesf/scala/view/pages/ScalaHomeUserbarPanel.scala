package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.wicket.application.SecuritySession
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.ToAjaxLink

class ScalaHomeUserbarPanel(id: String,
  homeCallback: IAjaxSimpleCallback,
  f_bookSearchPanel: String => Panel,
  f_loggedInPanel: String => Panel) extends Panel(id) {

  val AUTHENTICATION_PANEL = "authentication"

  this.initialize()

  def initialize() = {
    this.add(ToAjaxLink("homeLink", homeCallback))
    this.add(ToAjaxLink("titleLink", homeCallback))
    this.add(f_bookSearchPanel("searchbar"))
    if (this.getSession().asInstanceOf[SecuritySession].signedIn()) {
      this.add(f_loggedInPanel(AUTHENTICATION_PANEL))
    } else {
      this.add(new ScalaLoggedOutUserbarPanel(AUTHENTICATION_PANEL))
    }
  }

}