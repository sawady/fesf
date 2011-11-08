package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.IAjaxCallback

object ToAjaxLink {

  def apply(id: String, callback: IAjaxSimpleCallback): AjaxFallbackLink[String] = {
    return new AjaxFallbackLink[String](id) {
      override def onClick(target: AjaxRequestTarget) = {
        callback(target)
      }
    }
  }

  def apply[T](id: String, callback: IAjaxCallback[T], t: T): AjaxFallbackLink[String] = {
    new AjaxFallbackLink[String](id) {
      override def onClick(target: AjaxRequestTarget) = {
        callback(target, t)
      }
    }
  }

}