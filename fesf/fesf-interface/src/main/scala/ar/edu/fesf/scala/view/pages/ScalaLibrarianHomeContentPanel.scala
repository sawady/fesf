package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.scala.view.ScalaContainerPanel
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import ar.edu.fesf.scala.view.ReplaceablePanel
import ar.edu.fesf.model.Book
import ar.edu.fesf.scala.view.ToLazyPanel
import ar.edu.fesf.view.pages.persons.PersonInfoPanel
import ar.edu.fesf.model.Person
import java.util.List
import ar.edu.fesf.view.pages.librarian.LibrarianTabbedPanel
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.PersonService
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import java.util.ArrayList
import ar.edu.fesf.scala.view.AjaxNamedSimpleCallback
import ar.edu.fesf.view.pages.librarian.LibrarianHomeMainPanel
import ar.edu.fesf.view.pages.librarian.UsersPanel
import ar.edu.fesf.view.pages.loaning.LoaneesPanel

@AuthorizeInstantiation(Array("LIBRARIAN"))
class ScalaLibrarianHomeContentPanel(
  id: String,
  userHomeCallback: IAjaxSimpleCallback) extends ScalaContainerPanel(id) with ReplaceablePanel {

  override val CONTENT_ID: String = "content"

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  //Userbar
  val f_bookSearchPanel = new ScalaBookSearchPanel(_: String, changeToResultsPanel)

  //  //AuthenticatedUser
  val f_personInfoPanel = (id: String) => (person: Person) =>
    new PersonInfoPanel(id, person)

  val f_bookSearchResults = (id: String) =>
    (list: List[Book]) => ToLazyPanel(id, new ScalaHorizontalBookPanel(_: String, list, changeToMoreInfoPanel))

  val f_bookInfoPanel = (id: String) =>
    (book: Book) => new ScalaBookInfoPanel(id, bookService.initializeBookInfo(book), changeToMoreInfoPanel, changeToPersonInfo)

  this.initialize()
  private def initialize() = {

    val list = new ArrayList[AjaxNamedSimpleCallback]()
    list.add(new AjaxNamedSimpleCallback("Main Panel", this.changeToMainPanel()))
    list.add(new AjaxNamedSimpleCallback("Users", this.changeToUsersPanel()))
    list.add(new AjaxNamedSimpleCallback("Loanees", this.changeToLoaneesPanel()))

    this.add(new ScalaLibrarianOptionsPanel("sidebar", list))
    this.add(new ScalaLibrarianHomeUserbarPanel("userbar", userHomeCallback, f_bookSearchPanel))
    this.add(new ScalaLibrarianMainContentPanel(CONTENT_ID))
  }

  private def changeToPersonInfo(): IAjaxCallback[Person] =
    this.changeContent(f_personInfoPanel)

  private def changeToMoreInfoPanel(): IAjaxCallback[Book] =
    this.changeContent(f_bookInfoPanel)

  private def changeToResultsPanel(): IAjaxCallback[List[Book]] =
    this.changeContent(f_bookSearchResults)

  private def changeToMainPanel(): IAjaxSimpleCallback =
    this.changeContent(new ScalaLibrarianMainContentPanel(_: String))

  private def changeToUsersPanel(): IAjaxSimpleCallback =
    this.changeContent(new UsersPanel(_: String))

  private def changeToLoaneesPanel(): IAjaxSimpleCallback =
    this.changeContent(new LoaneesPanel(_: String))

}