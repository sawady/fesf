package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel
import org.apache.wicket.Component

object ToLazyPanel {

  def apply(id: String, f_panel: String => Panel): AjaxLazyLoadPanel = {
    return new AjaxLazyLoadPanel(id) {
      override def getLazyLoadComponent(innerID: String): Component = {
        return f_panel(innerID);
      }
    }
  }

}