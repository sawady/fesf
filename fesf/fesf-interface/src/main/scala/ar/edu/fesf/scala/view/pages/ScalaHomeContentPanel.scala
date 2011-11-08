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
import ar.edu.fesf.view.pages.books.BookInfoPanel
import org.apache.wicket.Component
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel
import ar.edu.fesf.view.pages.books.BookSearchResultPanel
import java.util.ArrayList
import java.util.List
import ar.edu.fesf.scala.view.ScalaAjaxReplacePanel
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import ar.edu.fesf.scala.view.ToLazyPanel
import ar.edu.fesf.scala.view.ScalaContainerPanel
import ar.edu.fesf.view.pages.home.CategoriesSidebar
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.view.pages.books.BookSearchPanel
import ar.edu.fesf.view.pages.loaning.LoaneeInfoPanel
import ar.edu.fesf.model.Person
import ar.edu.fesf.view.pages.persons.PersonInfoPanel
import ar.edu.fesf.view.pages.loaning.LoaningFormPanel
import ar.edu.fesf.model.Loan
import ar.edu.fesf.view.pages.generic.GenericFormPanel
import ar.edu.fesf.services.dtos.PersonDTO
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.view.pages.persons.PersonFormFieldsPanel
import ar.edu.fesf.wicket.application.SecuritySession

//TODO reificar callbacks
class ScalaHomeContentPanel(id: String) extends ScalaContainerPanel(id) {

  override val CONTENT_ID: String = "content"

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  //Generic
  val f_lazyPanel = ToLazyPanel(_: String, _: String => Panel)

  //Userbar
  val f_bookSearchPanel = new BookSearchPanel(_: String, oldChangeToResultsPanel)
  val f_homeLink = ToAjaxLink(changeToRankingPanel, _: String)

  //AuthenticatedUser
  lazy val loggedPerson = this.getSession().asInstanceOf[SecuritySession].getPerson()
  val f_signOutLink = ToAjaxLink(changeToRankingPanel, _: String)
  val f_profileLink = ToAjaxLink(changeToProfilePanel, _: String)
  val f_myLoansLink = ToAjaxLink(changeToLoaneeInfoPanel, _: String)
  val f_loggedInPanel =
    new ScalaLoggedInUserbarPanel(_: String, f_signOutLink, f_myLoansLink, f_profileLink)
  val f_personInfoPanel = (person: Person) =>
    new PersonInfoPanel(CONTENT_ID, this.personService.initializePersonInfo(person))

  //Loaning
  val f_loaneePanel = (id: String) =>
    new LoaneeInfoPanel(CONTENT_ID, this.personService.initializeLoaneeInfo(loggedPerson))
  val f_loaningFormPanel =
    new LoaningFormPanel(CONTENT_ID, _: Book, oldChangeToMoreInfoPanel)

  //MainContent
  val f_rankingPanel = f_lazyPanel(_: String, new ScalaRankingPanel(_: String, changeToMoreInfoPanel))
  val f_bookSearchResults = (id: String) =>
    (list: List[Book]) => f_lazyPanel(id, new BookSearchResultPanel(_: String, list, oldChangeToMoreInfoPanel))
  val f_bookInfoPanel = (id: String) =>
    (book: Book) => new ScalaBookInfoPanel(id, bookService.initializeBookInfo(book),
      changeToLoaningFormPanel, changeToMoreInfoPanel, oldChangeToLoaneeInfoPanel)

  /* initialization */
  this.initialize()

  private def initialize() = {
    this.add(f_rankingPanel(CONTENT_ID))
    this.add(new ScalaCategoriesSidebar("sidebar", changeToResultsPanel))
    this.add(new ScalaHomeUserbarPanel("userbar", f_homeLink, f_bookSearchPanel, f_loggedInPanel))
  }

  /* Callbacks for changing content */
  private def changeToRankingPanel: AjaxRequestTarget => Unit =
    this.changeContent(f_rankingPanel)

  private def oldChangeToMoreInfoPanel: ScalaAjaxReplacePanel[Book] =
    new ScalaAjaxReplacePanel(this, f_bookInfoPanel(CONTENT_ID))

  private def changeToMoreInfoPanel: (AjaxRequestTarget, Book) => Unit =
    this.changeContent(f_bookInfoPanel)

  private def changeToResultsPanel: (AjaxRequestTarget, List[Book]) => Unit =
    this.changeContent(f_bookSearchResults)

  private def oldChangeToResultsPanel: ScalaAjaxReplacePanel[List[Book]] =
    new ScalaAjaxReplacePanel(this, f_bookSearchResults(CONTENT_ID))

  private def changeToLoaneeInfoPanel: AjaxRequestTarget => Unit =
    this.changeContent(f_loaneePanel)

  private def oldChangeToLoaneeInfoPanel: ScalaAjaxReplacePanel[Person] =
    new ScalaAjaxReplacePanel(this, (person: Person) => f_loaneePanel(CONTENT_ID))

  private def changeToPersonInfo: ScalaAjaxReplacePanel[Person] =
    new ScalaAjaxReplacePanel(this, f_personInfoPanel)

  private def changeToLoaningFormPanel: ScalaAjaxReplacePanel[Book] =
    new ScalaAjaxReplacePanel(this, f_loaningFormPanel)

  private def changeToProfilePanel: AjaxRequestTarget => Unit = {
    this.changeContent((id: String) => new GenericFormPanel[PersonDTO](id) {
      override def getFieldsPanel(id: String): PanelServiceToForm[PersonDTO] = {
        new PersonFormFieldsPanel(id,
          new PersonDTO(personService.initializePersonInfo(getSession().asInstanceOf[SecuritySession].getPerson())),
          changeToPersonInfo);
      }
    })
  }

}