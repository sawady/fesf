package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel

class ReplaceablePanel(id: String) extends Panel(id) {

  this.setOutputMarkupId(true)

}