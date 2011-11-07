package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.IAjaxCallback
import scala.reflect.BeanProperty
import scala.annotation.target.beanGetter

abstract class ScalaContainerPanel(id: String) extends Panel(id) {

  val CONTENT_ID: String

  def changeContent(f_newPanel: String => Panel): AjaxRequestTarget => Unit =
    AjaxSimpleReplacePanel(f_newPanel(CONTENT_ID), this, _)

  def changeContent[T](f_newPanel: String => T => Panel): (AjaxRequestTarget, T) => Unit =
    (target: AjaxRequestTarget, t: T) => AjaxComplexReplacePanel(t, f_newPanel(CONTENT_ID), this, target)

}