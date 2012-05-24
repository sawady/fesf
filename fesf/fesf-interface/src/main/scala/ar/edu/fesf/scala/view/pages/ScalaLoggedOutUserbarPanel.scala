package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.protocol.http.WebApplication
import ar.edu.fesf.scala.view.application.MyApplication
import ar.edu.fesf.scala.view.application.MyApplication
import ar.edu.fesf.scala.view.application.MyApplication

class ScalaLoggedOutUserbarPanel(id: String) extends Panel(id) with ReplaceablePanel {

  this.add(new BookmarkablePageLink("signIn", WebApplication.get().asInstanceOf[MyApplication].loginPage()))
  this.add(new BookmarkablePageLink("signUp", WebApplication.get().asInstanceOf[MyApplication].loginPage()))

}