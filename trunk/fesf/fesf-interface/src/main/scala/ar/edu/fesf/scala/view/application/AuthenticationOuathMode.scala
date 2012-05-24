package ar.edu.fesf.scala.view.application
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.markup.html.WebPage
import ar.edu.fesf.scala.view.pages.SignIn

class AuthenticationOuathMode extends AuthenticationMode {
  
  def loginPage(): Class[_ <: WebPage] = classOf[SignIn]
  
  def securitySession(): Class[_ <: AuthenticatedWebSession] = classOf[SecuritySession]

}