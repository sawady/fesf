package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink

class ScalaLoggedOutUserbarPanel(id: String) extends Panel(id) with ReplaceablePanel {

  this.add(new BookmarkablePageLink("signIn", classOf[SignIn]))
  this.add(new BookmarkablePageLink("signUp", classOf[SignIn]))

}