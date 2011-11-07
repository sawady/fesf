package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget

object AjaxComplexReplacePanel {

  def apply[T](t: T, f_newPanel: T => Panel, parent: Panel, target: AjaxRequestTarget) = {
    val newPanel = f_newPanel(t)
    AjaxSimpleReplacePanel(newPanel, parent, target)
  }

}