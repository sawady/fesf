package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.controllers.GenericForm
import ar.edu.fesf.scala.view.ReplaceablePanel

@SerialVersionUID(1L)
class ScalaGenericFormPanel[T](
  id: String,
  f_formFieldPanel: String => PanelServiceToForm[T]) extends Panel(id) with Serializable with ReplaceablePanel {

  this.add(new GenericForm[T]("form", f_formFieldPanel("formFieldPanel")))

}