package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.IAjaxCallback

class ScalaContainerPanel(id: String) extends Panel(id) {

  def changeContent(newPanel: Panel): AjaxRequestTarget => Unit = {
    return AjaxSimpleReplacePanel(newPanel, this, _)
  }

  def changeContent[T](to_newPanel: T => Panel): IAjaxCallback[T] = {
    return new ScalaAjaxReplacePanel[T](this, to_newPanel)
  }

  def changePanelIfNotContained(id: String, child: Panel): AjaxRequestTarget => Unit = {
    if (this.contains(child, true)) {
      return this.changeContent(this.get(id).asInstanceOf[Panel])
    } else {
      return this.changeContent(child)
    }
  }

}