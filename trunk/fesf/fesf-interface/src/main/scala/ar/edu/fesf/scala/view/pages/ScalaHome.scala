package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.security.SecurityContextHelper
import scala.reflect.BeanProperty
import ar.edu.fesf.scala.view.application.SecuritySession
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import org.springframework.security.core.context.SecurityContextHolder
import ar.edu.fesf.security.FakeAuthentication

@SerialVersionUID(1L)
class ScalaHome extends WebPage with Serializable {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  /* initialization */
  this.initialize()

  private def initialize() = {

    //FAKE
    SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication());

    val mySession = this.getSession().asInstanceOf[SecuritySession]
    if (mySession.isAuthenticatedUser()) {
      var personDB = this.getPersonService().findPersonByEmail(mySession.getAuthenticatedUser().getEmail())
      if (personDB == null) {
        this.setResponsePage(classOf[SignUp])
      } else {
        mySession.attachPerson(personDB);
      }
    }

    if (mySession.isLibrarianSignedIn()) {
      this.add(new ScalaLibrarianHomeContentPanel("contentPanel", this.changeToUserHome()))
    } else {
      this.add(new ScalaHomeContentPanel("contentPanel"))
    }
  }

  private def changeToUserHome(): IAjaxSimpleCallback = {
    return new AjaxSimpleReplacePanel(this, new ScalaHomeContentPanel("contentPanel"))
  }

}