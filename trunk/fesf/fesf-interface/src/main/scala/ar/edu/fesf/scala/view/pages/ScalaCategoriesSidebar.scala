package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.markup.html.list.ListView
import ar.edu.fesf.model.Category
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.model.Book
import java.util.List
import ar.edu.fesf.controllers.IAjaxCallback

class ScalaCategoriesSidebar(id: String,
                             f_resultsCallback: IAjaxCallback[List[Book]])
  extends Panel(id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  this.initialize()

  def initialize() = {
    this.add(new ListView[Category]("links", this.bookService.findAllCategories()) {
      override def populateItem(item: ListItem[Category]) = {
        item.add(new CategoryLinkPanel("link", item.getModel(), f_resultsCallback))
      }
    })
  }

}