package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import ar.edu.fesf.view.pages.signin.SignIn
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink

class ScalaLoggedOutUserbarPanel(id: String) extends ReplaceablePanel(id) {

  this.add(new BookmarkablePageLink("signIn", classOf[SignIn]))
  this.add(new BookmarkablePageLink("signUp", classOf[SignIn]))

}