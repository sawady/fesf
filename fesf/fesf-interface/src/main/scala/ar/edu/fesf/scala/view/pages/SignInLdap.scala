package ar.edu.fesf.scala.view.pages

import java.io.Serializable
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.authroles.authentication.panel.SignInPanel
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.protocol.http.WebApplication
import ar.edu.fesf.scala.view.application.MyApplication


class SignInLdap extends WebPage with Serializable {

  this.add(new SignInPanel("signIn"))
  this.add(new BookmarkablePageLink("goHome", this.getApplication().getHomePage()))
}