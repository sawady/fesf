package ar.edu.fesf.scala.view.application
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.request.Request
import org.apache.wicket.injection.Injector
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.security.SecurityContextHelper
import ar.edu.fesf.model.Person
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import ar.edu.fesf.security.UserDetailsImpl

@SerialVersionUID(1L)
class SecuritySession(request: Request) extends AuthenticatedWebSession(request) with Serializable {

  Injector.get().inject(this);

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var securityContextHelper: SecurityContextHelper = _

  @BeanProperty
  var person: Person = null

  // It delegates authentication to Spring
  override def authenticate(username: String, password: String): Boolean = false

  override def getRoles(): Roles = {
    if (this.securityContextHelper.isAuthenticatedUser()) {
      return new Roles(this.getAuthenticatedUser().getAuthorities().iterator().next().getAuthority());
    }
    return null;
  }

  def attachPerson(aPerson: Person) = {
    this.setPerson(aPerson);
  }

  def getAuthenticatedUser(): UserDetailsImpl = {
    return this.securityContextHelper.getAuthenticatedUser();
  }

  def signedIn(): Boolean = this.getPerson() != null

  def isAuthenticatedUser(): Boolean = this.securityContextHelper.isAuthenticatedUser()

  def signOutPerson() = {
    this.setPerson(null);
  }

}