package ar.edu.fesf.scala.view
import ar.edu.fesf.controllers.IAjaxCallback
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import scala.reflect.Apply

object AjaxSimpleReplacePanel {

  def apply(newPanel: Panel, parent: Panel, target: AjaxRequestTarget) = {
    if (!parent.contains(newPanel, false)) {
      parent.replace(newPanel)
      target.add(newPanel)
    }
  }

}