package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.application.SecuritySession

class ScalaLibrarianHomeUserbarPanel(
  id: String,
  homeCallback: IAjaxSimpleCallback,
  f_bookSearchPanel: String => Panel) extends Panel(id) {

  this.initialize()
  def initialize() = {
    this.add(ToAjaxLink("homeLink", homeCallback))
    this.add(ToAjaxLink("titleLink", homeCallback))
    this.add(f_bookSearchPanel("searchbar"))
    this.add(new ScalaLoggedOutUserbarPanel("authentication"))
  }

}