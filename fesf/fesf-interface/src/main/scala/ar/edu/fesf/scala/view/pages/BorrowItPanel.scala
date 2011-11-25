package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.model.Book
import ar.edu.fesf.scala.view.application.SecuritySession
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction
import org.apache.wicket.markup.html.WebMarkupContainer
import ar.edu.fesf.scala.view.ReplaceablePanel
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import ar.edu.fesf.scala.view.ToAjaxSimpleCallback
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.model.ReservationEvent
import ar.edu.fesf.services.BookService

@AuthorizeAction(action = "RENDER", roles = Array("ROLE_USER"))
class BorrowItPanel(id: String,
  book: Book,
  canBorrowCallback: IAjaxSimpleCallback,
  cannotBorrowCallback: IAjaxCallback[Person]) extends Panel(id) with ReplaceablePanel {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  val f_borrowText = new Label("linkText", _: String)

  this.initialize()

  private def initialize() = {
    var personDB = this.getSession().asInstanceOf[SecuritySession].getPerson()
    if (personDB != null) {
      personDB = this.personService.initializeFields(personDB, "currentLoans", "email")
      if (personDB.cannotHaveMoreLoans()) {
        personDB = this.personService.initializeLoaneeInfo(personDB)
        this.add(ToAjaxLink("link", cannotBorrowCallback, personDB).add(f_borrowText("Cannot borrow another book, return one first")))
      } else {
        if (book.hasAvailableCopy()) {
          this.add(ToAjaxLink("link", canBorrowCallback).add(f_borrowText("Borrow It")))
        } else {
          if (this.bookService.isInTheReservationList(book, personDB)) {
            this.add(ToAjaxLink("link", this.showedInterestCallback()).add(f_borrowText("You are in the reservation list right now")).setEnabled(false))
          } else {
            this.add(ToAjaxLink("link", this.showedInterestCallback()).add(f_borrowText("Tell me when it gets available!")))
          }
        }
      }
    }
  }

  private def showedInterestCallback(): IAjaxSimpleCallback = {
    return ToAjaxSimpleCallback(this.changeBorrowItLink(_: AjaxRequestTarget))
  }

  private def changeBorrowItLink(target: AjaxRequestTarget) = {
    val person = this.getSession().asInstanceOf[SecuritySession].getPerson()
    this.bookService.addReservationEvent(book, person)
    this.replace(ToAjaxLink("link", null).add(f_borrowText("Done. You're in the list.")).setEnabled(false));
    target.add(this)
  }

}