package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.AjaxRequestTarget

object ToAjaxSimpleCallback {

  def apply(f: AjaxRequestTarget => Unit): IAjaxSimpleCallback = {
    new IAjaxSimpleCallback() {
      override def apply(target: AjaxRequestTarget) = {
        f(target)
      }
    }
  }

}