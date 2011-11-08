package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.AjaxRequestTarget

trait IAjaxSimpleCallback {

  def apply(target: AjaxRequestTarget): Unit

}