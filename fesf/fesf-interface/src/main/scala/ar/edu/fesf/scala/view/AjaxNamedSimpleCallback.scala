package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.AjaxRequestTarget
import scala.reflect.BeanProperty
import scala.annotation.target.beanGetter
import scala.annotation.target.getter

class AjaxNamedSimpleCallback(
  @BeanProperty var name: String,
  callback: IAjaxSimpleCallback) extends IAjaxSimpleCallback {

  override def apply(target: AjaxRequestTarget) = callback(target)

}