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

@AuthorizeAction(action = "RENDER", roles = Array("USER"))
class BorrowItPanel(id: String,
                    book: Book,
                    canBorrowCallback: IAjaxSimpleCallback,
                    cannotBorrowCallback: IAjaxCallback[Person]) extends Panel(id) with ReplaceablePanel {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  this.initialize()

  private def initialize() = {
    var personDB = this.getSession().asInstanceOf[SecuritySession].getPerson()
    val f_borrowText = new Label("linkText", _: String)
    if (personDB != null) {
      personDB = this.personService.initializeFields(personDB, "currentLoans")
      if (personDB.cannotHaveMoreLoans()) {
        personDB = this.personService.initializeLoaneeInfo(personDB)
        this.add(ToAjaxLink("link", cannotBorrowCallback, personDB).add(f_borrowText("Cannot borrow another book, return one first")))
      } else {
        this.add(ToAjaxLink("link", canBorrowCallback).add(f_borrowText("Borrow It")))
      }
    }
  }

}