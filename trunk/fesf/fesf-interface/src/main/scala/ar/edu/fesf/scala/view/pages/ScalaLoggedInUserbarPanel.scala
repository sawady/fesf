package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.scala.view.application.SecuritySession
import org.apache.wicket.markup.html.basic.Label
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person

class ScalaLoggedInUserbarPanel(id: String,
                                signOutCallback: IAjaxSimpleCallback,
                                myLoansCallback: IAjaxCallback[Person],
                                profileCallback: IAjaxCallback[Person]) extends ReplaceablePanel(id) {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  this.initialize()

  def initialize() = {
    val personName = this.getPerson().getName()
    val profileLink = ToAjaxLink("profile", profileCallback, this.personService.initializePersonInfo(this.getPerson()))
    profileLink.add(new Label("welcome", "Welcome "+personName))
    this.add(ToAjaxLink("signOut", signOutCallback))
    this.add(ToAjaxLink("myLoans", myLoansCallback, this.personService.initializeLoaneeInfo(this.getPerson())))
    this.add(profileLink)
  }

  private def getPerson(): Person = this.getSession().asInstanceOf[SecuritySession].getPerson()

}