package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink

class ScalaHomeUserbarPanel(id: String,
  f_homeLink: String => AjaxFallbackLink[String],
  f_bookSearchPanel: String => Panel) extends Panel(id) {

  this.add(f_homeLink("homeLink"))
  this.add(f_bookSearchPanel("searchbar"))

}