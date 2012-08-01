package ar.edu.fesf.scala.view.application

import java.io.Serializable
import scala.reflect.BeanProperty
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import ar.edu.fesf.security.LdapAuthentication
import ar.edu.fesf.security.UserManager
import ar.edu.fesf.services.RoleService
import ar.edu.fesf.security.UserDetailsLdapImpl


class SecurityLdapSession(request: Request) extends SecuritySession(request) with Serializable {

  @SpringBean(name = "ldapAuthProvider")
  @BeanProperty
  var ldapAuthProvider: AuthenticationProvider = _
  
  @SpringBean(name = "service.role")
  @BeanProperty
  var roleService: RoleService = _
  
  @SpringBean(name = "userManager")
  @BeanProperty
  var userManager: UserManager = _

  override def authenticate(username: String, password: String): Boolean = {
    try {
      var ldapAuthentication = this.getAuthentication(username, password);
      this.userManager.signUp(ldapAuthentication.getPrincipal().asInstanceOf[UserDetailsLdapImpl]);
      return this.updateSession(ldapAuthentication);
    } catch {
      case ioe: AuthenticationException => return false
    }
    return return false;
  }
  
  private def getAuthentication(username: String, password: String) : LdapAuthentication = {
    var authentication = this.ldapAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    return new LdapAuthentication(authentication, this.roleService);
  } 
  
  private def updateSession(ldapAuthentication: LdapAuthentication) : Boolean = {
    this.securityContextHelper.authentication(ldapAuthentication);
    return this.securityContextHelper.isAuthenticatedUser()
  }
  
}