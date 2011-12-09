package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.dtos.NewBookDTO
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import java.util.List

@SerialVersionUID(1L)
class ScalaHorizontalBookDTOPanel(
  id: String,
  books: List[NewBookDTO],
  callback: IAjaxCallback[NewBookDTO]) extends Panel(id) with ReplaceablePanel {

  this.initialize()

  private def initialize() = {
    this.add(new ListView[NewBookDTO]("bookList", this.books) {
      override def populateItem(item: ListItem[NewBookDTO]) = {
        val book = item.getModelObject()
        item.add(new ScalaBookDTOInfoMiniPanel("bookMiniPanel",
          book, callback))
      }
    })
  }

}