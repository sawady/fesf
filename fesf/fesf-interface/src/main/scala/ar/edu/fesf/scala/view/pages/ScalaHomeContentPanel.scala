package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.controllers.AjaxReplacePanel
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.services.BookService
import ar.edu.fesf.model.Book
import org.apache.wicket.Component
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel
import java.util.ArrayList
import java.util.List
import ar.edu.fesf.scala.view.ScalaAjaxReplacePanel
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import ar.edu.fesf.scala.view.ToLazyPanel
import ar.edu.fesf.scala.view.ScalaContainerPanel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.view.pages.loaning.LoaneeInfoPanel
import ar.edu.fesf.model.Person
import ar.edu.fesf.view.pages.persons.PersonInfoPanel
import ar.edu.fesf.view.pages.loaning.LoaningFormPanel
import ar.edu.fesf.model.Loan
import ar.edu.fesf.view.pages.generic.GenericFormPanel
import ar.edu.fesf.services.dtos.PersonDTO
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.view.pages.persons.PersonFormFieldsPanel
import ar.edu.fesf.scala.view.application.SecuritySession
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.ToGenericFormPanel

//TODO reificar callbacks
class ScalaHomeContentPanel(id: String) extends ScalaContainerPanel(id) {

  override val CONTENT_ID: String = "content"

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  //Userbar
  val f_bookSearchPanel = new ScalaBookSearchPanel(_: String, changeToResultsPanel)

  //AuthenticatedUser

  val f_personInfoPanel = (id: String) => (person: Person) =>
    new PersonInfoPanel(id, person)

  //Loaning
  val f_loaneePanel = (id: String) => (person: Person) =>
    new LoaneeInfoPanel(id, person)

  val f_loaningFormPanel = (id: String) => (book: Book) =>
    new LoaningFormPanel(id, book, changeToMoreInfoPanel)

  //MainContent
  val f_rankingPanel = ToLazyPanel(_: String, new ScalaRankingPanel(_: String, changeToMoreInfoPanel))

  val f_bookSearchResults = (id: String) =>
    (list: List[Book]) => ToLazyPanel(id, new ScalaHorizontalBookPanel(_: String, list, changeToMoreInfoPanel))

  val f_bookInfoPanel = (id: String) =>
    (book: Book) => new ScalaBookInfoPanel(id, bookService.initializeBookInfo(book),
      changeToLoaningFormPanel, changeToMoreInfoPanel, changeToLoaneeInfoPanel)

  /* initialization */
  this.initialize()

  private def initialize() = {
    this.add(f_rankingPanel(CONTENT_ID))
    this.add(new ScalaCategoriesSidebar("sidebar", changeToResultsPanel))
    this.add(new ScalaHomeUserbarPanel("userbar", changeToRankingPanel, f_bookSearchPanel,
      changeToRankingPanel, changeToLoaneeInfoPanel, changeToProfilePanel))
  }

  /* Callbacks for changing content */
  private def changeToRankingPanel(): IAjaxSimpleCallback =
    this.changeContent(f_rankingPanel)

  private def changeToMoreInfoPanel(): IAjaxCallback[Book] =
    this.changeContent(f_bookInfoPanel)

  private def changeToResultsPanel(): IAjaxCallback[List[Book]] =
    this.changeContent(f_bookSearchResults)

  private def changeToLoaneeInfoPanel(): IAjaxCallback[Person] =
    this.changeContent(f_loaneePanel)

  private def changeToPersonInfo(): IAjaxCallback[Person] =
    this.changeContent(f_personInfoPanel)

  private def changeToLoaningFormPanel(): IAjaxCallback[Book] =
    this.changeContent(f_loaningFormPanel)

  private def changeToProfilePanel(): IAjaxCallback[Person] = {
    this.changeContent((id: String) => (person: Person) =>
      ToGenericFormPanel(id, new PersonFormFieldsPanel(_: String, new PersonDTO(person))))
  }

  private def loggedPerson(): Person = this.getSession().asInstanceOf[SecuritySession].getPerson()

}