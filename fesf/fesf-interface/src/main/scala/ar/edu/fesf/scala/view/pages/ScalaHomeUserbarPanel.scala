package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.scala.view.application.SecuritySession
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.basic.Label

class ScalaHomeUserbarPanel(
  id: String,
  f_bookSearchPanel: String => Panel,
  changeToRankingPanel: IAjaxSimpleCallback,
  changeToLoaneeInfoPanel: IAjaxCallback[Person],
  changeToProfilePanel: IAjaxCallback[Person])
  extends Panel(id) {

  val AUTHENTICATION_PANEL = "authentication"

  this.initialize()
  def initialize() = {
    val mySession = this.getSession().asInstanceOf[SecuritySession]
    val homeClass = getApplication().getHomePage()
    val homeLink = new BookmarkablePageLink("homeLink", homeClass)
    if (mySession.isLibrarianSignedIn()) {
      homeLink.add(new Label("homeLinkText", "Librarian Home"));
    } else {
      homeLink.add(new Label("homeLinkText", "Home"));
    }
    this.add(homeLink)
    this.add(new BookmarkablePageLink("titleLink", homeClass))
    this.add(f_bookSearchPanel("searchbar"))
    if (mySession.signedIn()) {
      this.add(new ScalaLoggedInUserbarPanel(AUTHENTICATION_PANEL, changeToRankingPanel, changeToLoaneeInfoPanel, changeToProfilePanel))
    } else {
      this.add(new ScalaLoggedOutUserbarPanel(AUTHENTICATION_PANEL))
    }
  }

}