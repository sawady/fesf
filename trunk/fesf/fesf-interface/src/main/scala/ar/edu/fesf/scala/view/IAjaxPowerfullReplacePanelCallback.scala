package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.panel.Panel

trait IAjaxPowerfullReplacePanelCallback {

  def apply(newPanel: Panel, panel: Panel, target: AjaxRequestTarget)

}