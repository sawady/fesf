package ar.edu.fesf.scala.view.application
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession

trait AuthenticationMode {
  
  def loginPage(): Class[_ <: WebPage]
  
  def securitySession(): Class[_ <: AuthenticatedWebSession]

}