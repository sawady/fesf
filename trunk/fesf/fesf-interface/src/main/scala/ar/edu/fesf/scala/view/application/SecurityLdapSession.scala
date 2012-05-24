package ar.edu.fesf.scala.view.application

import java.io.Serializable
import scala.reflect.BeanProperty
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import ar.edu.fesf.security.UserDetailsLdapImpl
import ar.edu.fesf.security.LdapAuthentication


class SecurityLdapSession(request: Request) extends SecuritySession(request) with Serializable {

  @SpringBean(name = "ldapAuthProvider")
  @BeanProperty
  var ldapAuthProvider: AuthenticationProvider = _

  override def authenticate(username: String, password: String): Boolean = {
    try {
      var authentication = this.ldapAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      this.securityContextHelper.authentication(new LdapAuthentication(authentication));
      return this.securityContextHelper.isAuthenticatedUser()
    } catch {
      case ioe: AuthenticationException => return false
    }
    return return false;
  }
  
}