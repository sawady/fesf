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
import java.util.Iterator
import java.util.ArrayList
import scala.collection.JavaConversions._
import java.util.HashSet
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.markup.html.form.DropDownChoice

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

  var rawCategories = new ArrayList[String]()
  var rawAuthors = new ArrayList[String]()

  /* initialization */
  this.initialize()
  private def initialize() = {

    this.rawCategories.addAll(editBookDTO.getCategories())
    this.rawAuthors.addAll(editBookDTO.getAuthors())

    this.add(new RequiredTextField[String]("title"))
    this.add(new RequiredTextField[String]("isbn"))
    this.add(new TextArea[String]("description"))
    this.add(new CheckBox("available"));

    this.add(new DropDownChoice[String]("location", this.bookService.booksLocation()))
    this.add(getAutoCompleteTextField("publisher", bookService.getPublishersNamedLike(_: String)).setRequired(true))

    this.add(new RepeatedTextFieldPanel("categories", this.rawCategories,
      getAutoCompleteTextField(_, _, bookService.getCategoriesNamedLike(_: String))))
    this.add(new RepeatedTextFieldPanel("authors", this.rawAuthors,
      getAutoCompleteTextField(_, _, bookService.getAuthorsNamedLike(_: String))))

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