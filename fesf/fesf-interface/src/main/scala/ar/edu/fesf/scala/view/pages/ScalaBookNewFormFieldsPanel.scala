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
import java.util.Iterator
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.Form
import ar.edu.fesf.services.BookService
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import java.util.ArrayList
import scala.collection.JavaConversions._
import org.apache.wicket.model.IModel
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.form.DropDownChoice

class ScalaBookNewFormFieldsPanel(
  id: String,
  submitCallback: IAjaxCallback[Book],
  newBookDTO: NewBookDTO) extends PanelServiceToForm[NewBookDTO](id) with ReplaceablePanel {

  def this(id: String, submitCallback: IAjaxCallback[Book]) = this(id, submitCallback, new NewBookDTO())

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  var rawCategories = new ArrayList[String]()
  var rawAuthors = new ArrayList[String]()

  this.initialize()
  private def initialize() {

    this.rawCategories.addAll(newBookDTO.getCategories())
    this.rawAuthors.addAll(newBookDTO.getAuthors())

    this.add(new RequiredTextField[String]("title"))
    this.add(new RequiredTextField[String]("isbn"))
    this.add(new RequiredTextField[Integer]("countOfCopies", classOf[Integer]).add(new MinimumValidator[Integer](1)))
    this.add(new TextArea[String]("description"))
    this.add(new DropDownChoice[String]("location", this.bookService.booksLocation()))

    this.add(getAutoCompleteTextField("publisher", bookService.getPublishersNamedLike(_: String)).setRequired(true))

    this.add(new RepeatedTextFieldPanel("categories", this.rawCategories,
      getAutoCompleteTextField(_, _, bookService.getCategoriesNamedLike(_: String))))
    this.add(new RepeatedTextFieldPanel("authors", this.rawAuthors,
      getAutoCompleteTextField(_, _, bookService.getAuthorsNamedLike(_: String))))

    this.add(new CheckBox("available"));
  }

  private def getAutoCompleteTextField(id: String, f_service: (String) => Iterator[String]): TextField[String] = {
    this.getAutoCompleteTextField(id, null, f_service)
  }

  private def getAutoCompleteTextField(id: String, model: IModel[String], f_service: (String) => Iterator[String]): TextField[String] = {
    return new AutoCompleteTextField[String](id, model) {
      override def getChoices(input: String): Iterator[String] = {
        return f_service(input);
      }
    }
  }

  override def getObject(): NewBookDTO = this.newBookDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[NewBookDTO]) = {

    for (category <- this.rawCategories) {
      this.newBookDTO.addCategory(category)
    }

    for (author <- this.rawAuthors) {
      this.newBookDTO.addAuthor(author)
    }

    val newBook = this.getBookService().registerNewBookDTO(this.newBookDTO);
    submitCallback(target, newBook);
  }

}