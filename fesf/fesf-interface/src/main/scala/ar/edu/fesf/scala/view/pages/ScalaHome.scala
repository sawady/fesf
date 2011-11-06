package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.security.SecurityContextHelper
import scala.reflect.BeanProperty

@SerialVersionUID(1L)
class ScalaHome extends WebPage {

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean(name = "service.SecurityContextHelper")
  @BeanProperty
  var securityContextHelper: SecurityContextHelper = _

  /* initialization */
  this.add(new ScalaHomeContentPanel("contentPanel"))

}