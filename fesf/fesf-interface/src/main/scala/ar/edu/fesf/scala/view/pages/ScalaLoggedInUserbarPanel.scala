package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.wicket.application.SecuritySession
import org.apache.wicket.markup.html.basic.Label

class ScalaLoggedInUserbarPanel(id: String,
  f_signOutLink: String => AjaxFallbackLink[_],
  f_myLoansLink: String => AjaxFallbackLink[_],
  f_profileLink: String => AjaxFallbackLink[_]) extends ReplaceablePanel(id) {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  this.initialize()

  def initialize() = {
    val personName = this.getSession().asInstanceOf[SecuritySession].getPerson().getName()
    val profileLink = f_profileLink("profile")
    profileLink.add(new Label("welcome", "Welcome " + personName))
    this.add(f_signOutLink("signOut"), f_myLoansLink("myLoans"), profileLink)
  }

}