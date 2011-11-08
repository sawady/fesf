package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.IAjaxCallback

object ToAjaxLink {

  def apply(f: AjaxRequestTarget => Unit, id: String): AjaxFallbackLink[String] = {
    return new AjaxFallbackLink[String](id) {
      override def onClick(target: AjaxRequestTarget) = {
        f(target)
      }
    }
  }

  def apply[T](id: String, f: IAjaxCallback[T], t: T): AjaxFallbackLink[String] = {
    new AjaxFallbackLink[String](id) {
      override def onClick(target: AjaxRequestTarget) = {
        f(target, t)
      }
    }
  }

}