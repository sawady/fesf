package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.dtos.EditBookDTO
import ar.edu.fesf.services.BookService
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.RequiredTextField
import scala.reflect.BeanProperty
import org.apache.wicket.markup.html.form.TextArea
import ar.edu.fesf.model.Book
import ar.edu.fesf.controllers.IAjaxCallback
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField
import ar.edu.fesf.services.PublisherService
import java.util.Iterator
import java.util.ArrayList
import scala.collection.JavaConversions._
import java.util.HashSet

@SerialVersionUID(1L)
class ScalaBookEditFormFieldsPanel(
  id: String,
  book: Book,
  callback: IAjaxCallback[Book])
  extends PanelServiceToForm[EditBookDTO](id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  @SpringBean
  @BeanProperty
  var publisherService: PublisherService = _

  val editBookDTO = new EditBookDTO(book)

  var rawCategories = new ArrayList[String]()
  var rawAuthors = new ArrayList[String]()

  /* initialization */
  this.initialize()
  private def initialize() = {

    this.rawCategories.addAll(editBookDTO.getCategories())
    this.rawAuthors.addAll(editBookDTO.getAuthors())

    this.add(new RequiredTextField[String]("title"))
    this.add(new RequiredTextField[String]("isbn"))
    this.add(new AutoCompleteTextField[String]("publisher", classOf[String]) {
      override def getChoices(input: String): Iterator[String] = {
        return publisherService.getPublishersNamedLike(input);
      }
    }.setRequired(true))
    this.add(new TextArea[String]("description"))
    this.add(new CheckBox("available"));

    this.add(new RepeatedTextFieldPanel("categories", this.rawCategories))
    this.add(new RepeatedTextFieldPanel("authors", this.rawAuthors))

  }

  override def getObject() = this.editBookDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[EditBookDTO]) = {

    this.editBookDTO.setCategories(new HashSet())
    this.editBookDTO.setAuthors(new HashSet())

    for (category <- this.rawCategories) {
      this.editBookDTO.addCategory(category)
    }

    for (author <- this.rawAuthors) {
      this.editBookDTO.addAuthor(author)
    }

    val bookDB = this.bookService.registerEditBookDTO(this.editBookDTO)
    callback(target, bookDB)
  }

}