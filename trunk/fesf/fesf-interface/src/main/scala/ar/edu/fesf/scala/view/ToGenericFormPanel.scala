package ar.edu.fesf.scala.view
import ar.edu.fesf.view.pages.generic.GenericFormPanel
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.controllers.PanelServiceToForm

object ToGenericFormPanel {

  def apply[T](id: String, f_fieldsPanel: String => PanelServiceToForm[T]) = {
    new GenericFormPanel[T](id) {
      override def getFieldsPanel(id: String): PanelServiceToForm[T] = {
        f_fieldsPanel(id)
      }
    }
  }

}