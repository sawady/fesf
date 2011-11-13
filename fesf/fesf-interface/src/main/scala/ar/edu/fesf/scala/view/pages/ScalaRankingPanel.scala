package ar.edu.fesf.scala.view.pages
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book
import java.util.List
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.panel.Panel

@SerialVersionUID(1L)
class ScalaRankingPanel(id: String,
                        callback: IAjaxCallback[Book])
  extends Panel(id) with ReplaceablePanel {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  /* initialization */
  this.updateContent()

  def updateContent() = {
    // para romper las bolas hago esta funcion
    val f_horizontalPanel = (id: String, books: List[Book]) => new ScalaHorizontalBookPanel(id, books, callback)
    this.addOrReplace(f_horizontalPanel("top20", this.getBookService().getTop20()))
    this.addOrReplace(f_horizontalPanel("recentlyAvailable", this.getBookService().getRecentlyAvailable()))
  }

  def withUpdatedContent(): ScalaRankingPanel = {
    this.updateContent()
    return this
  }

}