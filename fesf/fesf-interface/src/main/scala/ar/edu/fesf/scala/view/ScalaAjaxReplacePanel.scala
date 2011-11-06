package ar.edu.fesf.scala.view
import ar.edu.fesf.controllers.AjaxReplacePanel
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.AjaxReplacePanel

class ScalaAjaxReplacePanel[T](parent: Panel, f: T => Panel) extends AjaxReplacePanel[T](parent) {

  override def getNewPanel(t: T): Panel = {
    return f(t)
  }

}