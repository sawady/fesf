package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.scala.view.ScalaContainerPanel
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import ar.edu.fesf.scala.view.ReplaceablePanel
import ar.edu.fesf.model.Book
import ar.edu.fesf.scala.view.ToLazyPanel
import ar.edu.fesf.view.pages.persons.PersonInfoPanel
import ar.edu.fesf.model.Person
import java.util.List
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.PersonService
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import java.util.ArrayList
import ar.edu.fesf.scala.view.AjaxNamedSimpleCallback
import ar.edu.fesf.view.pages.loaning.LoaneesPanel
import ar.edu.fesf.view.pages.generic.GenericFormPanel
import ar.edu.fesf.dtos.NewBookDTO
import ar.edu.fesf.view.pages.loaning.LoaneeInfoPanel
import ar.edu.fesf.view.pages.persons.PersonFormFieldsPanel
import ar.edu.fesf.dtos.PersonDTO
import ar.edu.fesf.dtos.EditBookDTO

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

  //Loaning
  val f_loaneePanel = (id: String) => (person: Person) =>
    new LoaneeInfoPanel(id, person)

  //AuthenticatedUser
  val f_personInfoPanel = (id: String) => (person: Person) =>
    new PersonInfoPanel(id, person)

  val f_bookSearchResults = (id: String) =>
    (list: List[Book]) => ToLazyPanel(id, new ScalaHorizontalBookPanel(_: String, list, changeToEditBookPanel))

  val f_bookInfoPanel = (id: String) =>
    (book: Book) => new ScalaBookInfoPanel(id, bookService.initializeBookInfo(book), changeToMoreInfoPanel, changeToPersonInfo)

  this.initialize()
  private def initialize() = {

    val sidebarCallbackList = new ArrayList[AjaxNamedSimpleCallback]()
    sidebarCallbackList.add(new AjaxNamedSimpleCallback("Main Panel", this.changeToLoaneesPanel()))
    sidebarCallbackList.add(new AjaxNamedSimpleCallback("New Book", this.changeToNewBookPanel()))
    //    list.add(new AjaxNamedSimpleCallback("Loanees", this.changeToLoaneesPanel()))

    // TODO esto de aca abajo
    //    list.add(new AjaxNamedSimpleCallback("Users", this.changeToUsersPanel()))

    this.add(new ScalaLibrarianOptionsPanel("sidebar", sidebarCallbackList))
    this.add(new ScalaLibrarianHomeUserbarPanel("userbar", userHomeCallback, f_bookSearchPanel,
      userHomeCallback, changeToLoaneeInfoPanel, changeToProfilePanel))
    this.add(new LoaneesPanel(CONTENT_ID))
  }

  private def changeToPersonInfo(): IAjaxCallback[Person] =
    this.changeContent(f_personInfoPanel)

  private def changeToLoaneeInfoPanel(): IAjaxCallback[Person] =
    this.changeContent(f_loaneePanel)

  private def changeToProfilePanel(): IAjaxCallback[Person] = {
    this.changeContent((id: String) => (person: Person) =>
      new ScalaGenericFormPanel(id, new PersonFormFieldsPanel(_: String, new PersonDTO(person))))
  }

  private def changeToMoreInfoPanel(): IAjaxCallback[Book] =
    this.changeContent(f_bookInfoPanel)

  private def changeToResultsPanel(): IAjaxCallback[List[Book]] =
    this.changeContent(f_bookSearchResults)

  private def changeToMainPanel(): IAjaxSimpleCallback =
    this.changeContent(new ScalaLibrarianMainContentPanel(_: String))

  private def changeToLoaneesPanel(): IAjaxSimpleCallback =
    this.changeContent(new LoaneesPanel(_: String))

  private def changeToNewBookPanel(): IAjaxSimpleCallback = {
    this.changeContent(new ScalaGenericFormPanel[NewBookDTO](_: String,
      (innerID: String) => new ScalaBookNewFormFieldsPanel(innerID, this.changeToMoreInfoPanel())))
  }

  private def changeToEditBookPanel(): IAjaxCallback[Book] = {
    this.changeContent((id: String) => (book: Book) => new ScalaGenericFormPanel[EditBookDTO](id,
      (innerID: String) => new ScalaBookEditFormFieldsPanel(innerID, book, this.changeToMoreInfoPanel())))
  }

}