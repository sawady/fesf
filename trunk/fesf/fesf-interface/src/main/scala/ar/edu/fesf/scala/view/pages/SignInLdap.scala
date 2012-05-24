package ar.edu.fesf.scala.view.pages

import java.io.Serializable
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.authroles.authentication.panel.SignInPanel

class SignInLdap extends WebPage with Serializable {
  
  this.add(new SignInPanel("signIn"))

}