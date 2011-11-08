package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import java.util.List
import ar.edu.fesf.model.Book
import ar.edu.fesf.controllers.IAjaxCallback
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import ar.edu.fesf.view.pages.books.BookInfoMiniPanel
import org.apache.wicket.ajax.AjaxRequestTarget

@SerialVersionUID(1L)
class ScalaHorizontalBookPanel(id: String,
                               books: List[Book],
                               callback: IAjaxCallback[Book]) extends Panel(id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  this.add(new ListView[Book]("bookList", this.books) {
    override def populateItem(item: ListItem[Book]) = {
      val book = item.getModelObject()
      item.add(new ScalaBookInfoMiniPanel("bookMiniPanel",
        bookService.initializeBookInfo(book), callback))
    }
  })

}