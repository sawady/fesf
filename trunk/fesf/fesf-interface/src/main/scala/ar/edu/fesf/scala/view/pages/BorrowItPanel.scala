package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.model.Book
import ar.edu.fesf.wicket.application.SecuritySession
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.scala.view.IAjaxSimpleCallback

class BorrowItPanel(id: String,
  book: Book,
  canBorrowCallback: IAjaxCallback[Book],
  cannotBorrowCallback: IAjaxCallback[Person]) extends Panel(id) {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  this.initialize()

  private def initialize() = {
    var personDB = this.getSession().asInstanceOf[SecuritySession].getPerson()
    val f_borrowText = new Label("linkText", _: String)
    if (personDB == null) {
      this.setVisible(false)
      //TODO: esto va con authorization-wicket
      this.add(ToAjaxLink("link", null, null).add(f_borrowText("")))
    } else {
      personDB = this.personService.initializeFields(personDB, "currentLoans")
      if (personDB.cannotHaveMoreLoans()) {
        personDB = this.personService.initializeLoaneeInfo(personDB)
        this.add(ToAjaxLink("link", cannotBorrowCallback, personDB).add(f_borrowText("Cannot borrow a book, return one first")))
      } else {
        this.add(ToAjaxLink("link", canBorrowCallback, book).add(f_borrowText("Borrow It")))
      }
    }
  }

}