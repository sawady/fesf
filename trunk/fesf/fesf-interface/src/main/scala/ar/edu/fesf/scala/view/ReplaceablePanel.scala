package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel

class ReplaceablePanel(id: String, model: IModel[_]) extends Panel(id, model) {

  def this(id: String) = this(id, null)

  this.setOutputMarkupId(true)

}