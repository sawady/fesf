package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel

trait ReplaceablePanel extends Panel {

  this.setOutputMarkupId(true)

}