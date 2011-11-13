package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.AjaxRequestTarget

trait IAjaxSimpleCallback extends Serializable {

  def apply(target: AjaxRequestTarget): Unit

}