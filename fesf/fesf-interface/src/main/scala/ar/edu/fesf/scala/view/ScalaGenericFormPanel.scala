package ar.edu.fesf.scala.view
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.controllers.GenericForm

@SerialVersionUID(1L)
class ScalaGenericFormPanel[T](
  id: String,
  f_formFieldPanel: String => PanelServiceToForm[T]) extends Panel(id) with Serializable {

  this.setOutputMarkupId(true)
  this.add(new GenericForm[T]("form", f_formFieldPanel("formFieldPanel")))

}