package ar.edu.fesf.view.pages.books
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.services.dtos.EditBookDTO
import ar.edu.fesf.services.BookService
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.RequiredTextField
import scala.reflect.BeanProperty
import org.apache.wicket.markup.html.form.TextArea

@SerialVersionUID(1L)
class BookEditFormFieldPanel(id: String, val editBookDTO: EditBookDTO, val callback: AjaxRequestTarget => Void)
  extends PanelServiceToForm[EditBookDTO](id) {

  @SpringBean(name = "service.book")
  @BeanProperty
  var bookService: BookService = null

  /* initialization */
  this.add(new RequiredTextField[String]("title"),
    new RequiredTextField[String]("isbn"),
    new RequiredTextField[String]("publisher"),
    new TextArea[String]("description"))

  override def getObject() = this.editBookDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[EditBookDTO]) = {
    this.bookService.registerEditBookDTO(this.editBookDTO)
    this.callback(target)
  }

}