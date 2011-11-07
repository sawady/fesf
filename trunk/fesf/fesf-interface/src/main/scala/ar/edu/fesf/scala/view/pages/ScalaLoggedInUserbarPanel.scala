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
  f_signOut: String => AjaxFallbackLink[_],
  f_myLoans: String => AjaxFallbackLink[_],
  f_profile: String => AjaxFallbackLink[_]) extends ReplaceablePanel(id) {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  this.initialize()

  def initialize() = {
    val personName = this.getSession().asInstanceOf[SecuritySession].getPerson().getName()
    val profileLink = f_profile("profile")
    profileLink.add(new Label("welcome", "Welcome " + personName))
    this.add(f_signOut("signOut"), f_myLoans("myLoans"), profileLink)
  }

}