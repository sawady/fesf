package ar.edu.fesf.scala.view
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget

object ToAjaxLink {

  def apply(f: AjaxRequestTarget => Unit, id: String): AjaxFallbackLink[String] = {
    return new AjaxFallbackLink[String](id) {
      override def onClick(target: AjaxRequestTarget) = {
        f(target)
      }
    }
  }

}