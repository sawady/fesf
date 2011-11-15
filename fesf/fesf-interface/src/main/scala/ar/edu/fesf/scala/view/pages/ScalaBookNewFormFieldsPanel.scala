package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.dtos.NewBookDTO
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.validation.validator.MinimumValidator
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PublisherService
import java.util.Iterator
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.Form
import ar.edu.fesf.services.BookService
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.form.CheckBox

class ScalaBookNewFormFieldsPanel(
  id: String,
  submitCallback: IAjaxCallback[Book]) extends PanelServiceToForm[NewBookDTO](id) with ReplaceablePanel {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  @SpringBean
  @BeanProperty
  var publisherService: PublisherService = _

  val newBookDTO = new NewBookDTO()

  this.initialize()
  private def initialize() {
    this.add(new RequiredTextField[String]("title"))
    this.add(new RequiredTextField[String]("isbn"))
    this.add(new RequiredTextField[Integer]("countOfCopies", classOf[Integer]).add(new MinimumValidator[Integer](1)))
    this.add(new TextArea[String]("description"))
    this.add(new AutoCompleteTextField[String]("publisher", classOf[String]) {
      override def getChoices(input: String): Iterator[String] = {
        return publisherService.getPublishersNamedLike(input);
      }
    }.setRequired(true))
    this.add(new CheckBox("available"));
  }

  override def getObject(): NewBookDTO = this.newBookDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[NewBookDTO]) = {
    val newBook = this.getBookService().registerNewBookDTO(this.newBookDTO);
    submitCallback(target, newBook);
  }

}