package ar.edu.fesf.scala.view.application
import org.apache.wicket.markup.html.WebPage
import ar.edu.fesf.scala.view.pages.SignInLdap
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession

class AuthenticationLdapMode extends AuthenticationMode {
  
  def loginPage(): Class[_ <: WebPage] = classOf[SignInLdap]
  
  def securitySession(): Class[_ <: AuthenticatedWebSession] = classOf[SecurityLdapSession]

}