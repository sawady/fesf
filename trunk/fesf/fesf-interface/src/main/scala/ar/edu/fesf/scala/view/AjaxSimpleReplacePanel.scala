package ar.edu.fesf.scala.view
import ar.edu.fesf.controllers.IAjaxCallback
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import scala.reflect.Apply

class AjaxSimpleReplacePanel(parent: Panel, newPanel: Panel) extends IAjaxSimpleCallback {

  override def apply(target: AjaxRequestTarget) = {
    //    if (!parent.contains(newPanel, false)) {
    parent.replace(newPanel)
    target.add(newPanel)
    //    }
  }

}