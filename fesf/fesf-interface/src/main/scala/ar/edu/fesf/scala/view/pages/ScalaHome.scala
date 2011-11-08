package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.security.SecurityContextHelper
import scala.reflect.BeanProperty
import ar.edu.fesf.wicket.application.SecuritySession

@SerialVersionUID(1L)
class ScalaHome extends WebPage {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  /* initialization */
  this.initialize()

  private def initialize() = {
    val mySession = this.getSession().asInstanceOf[SecuritySession]
    if (mySession.isSignedIn()) {
      val personDB = this.getPersonService().findPersonByEmail(mySession.getAuthenticatedUser().getEmail())
      if (personDB == null) {
        this.setResponsePage(classOf[SignUp])
      } else {
        mySession.attachPerson(personDB);
      }
    }
    this.add(new ScalaHomeContentPanel("contentPanel"))
  }

}