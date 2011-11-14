package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.services.dtos.EditBookDTO
import ar.edu.fesf.services.BookService
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.RequiredTextField
import scala.reflect.BeanProperty
import org.apache.wicket.markup.html.form.TextArea
import ar.edu.fesf.model.Book
import ar.edu.fesf.controllers.IAjaxCallback

@SerialVersionUID(1L)
class ScalaBookEditFormFieldsPanel(
  id: String,
  book: Book,
  callback: IAjaxCallback[Book])
  extends PanelServiceToForm[EditBookDTO](id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  val editBookDTO = new EditBookDTO(book)

  /* initialization */
  this.initialize()
  private def initialize() = {
    this.add(new RequiredTextField[String]("title"))
    this.add(new RequiredTextField[String]("isbn"))
    this.add(new RequiredTextField[String]("publisher"))
    this.add(new TextArea[String]("description"))
  }

  override def getObject() = this.editBookDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[EditBookDTO]) = {
    val bookDB = this.bookService.registerEditBookDTO(this.editBookDTO)
    callback(target, bookDB)
  }

}