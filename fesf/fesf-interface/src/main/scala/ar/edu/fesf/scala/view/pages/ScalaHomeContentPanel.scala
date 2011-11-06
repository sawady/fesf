package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.controllers.AjaxReplacePanel
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.PersonService
import ar.edu.fesf.services.BookService
import ar.edu.fesf.model.Book
import ar.edu.fesf.view.pages.books.BookInfoPanel
import org.apache.wicket.Component
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel
import ar.edu.fesf.view.pages.books.BookSearchResultPanel
import java.util.ArrayList
import java.util.List
import ar.edu.fesf.scala.view.ScalaAjaxReplacePanel
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import ar.edu.fesf.scala.view.ToLazyPanel
import ar.edu.fesf.scala.view.ScalaContainerPanel
import ar.edu.fesf.view.pages.home.CategoriesSidebar
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import ar.edu.fesf.scala.view.ToAjaxLink
import ar.edu.fesf.view.pages.books.BookSearchPanel

class ScalaHomeContentPanel(id: String) extends ScalaContainerPanel(id) {

  val CONTENT: String = "content"

  @SpringBean
  @BeanProperty
  var personService: PersonService = _

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  val f_lazyPanel = ToLazyPanel(CONTENT, _: String => Panel)
  val f_rankingPanel = new ScalaRankingPanel(_: String, changeToMoreInfoPanel)
  val f_bookSearchResults = new BookSearchResultPanel(_: String, _: List[Book], changeToMoreInfoPanel)
  val f_bookSearchPanel = new BookSearchPanel(_: String, changeToResultsPanel)
  val f_homeLink = ToAjaxLink(changeToRankingPanel, _: String)

  /* initialization */
  this.initialize

  private def initialize = {
    this.add(f_lazyPanel(f_rankingPanel))
    this.add(new CategoriesSidebar("sidebar", changeToResultsPanel))
    this.add(new ScalaHomeUserbarPanel("userbar", f_homeLink, f_bookSearchPanel))
  }

  private def changeToRankingPanel: AjaxRequestTarget => Unit = {
    return this.changePanelIfNotContained(CONTENT,
      ToLazyPanel(CONTENT, this.f_rankingPanel))
  }

  private def changeToMoreInfoPanel: IAjaxCallback[Book] = {
    return this.changeContent[Book](
      (book: Book) => new BookInfoPanel(CONTENT, bookService.initializeBookInfo(book), null, null, null))
  }

  private def changeToResultsPanel: IAjaxCallback[List[Book]] = {
    return this.changeContent[List[Book]]((list: List[Book]) =>
      ToLazyPanel(CONTENT, f_bookSearchResults(_, list)))
  }

}