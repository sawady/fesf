package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.services.BookService
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.model.Comment
import scala.reflect.BeanProperty
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book
import java.util.List
import ar.edu.fesf.scala.view.pages.CommentPanel

class CommentListPanel(id: String, comments: List[Comment]) extends Panel(id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  initialize()

  def initialize() = {
    add(new ListView[Comment]("commentList", comments) {
      @Override
      def populateItem(item: ListItem[Comment]) {
        val comment: Comment = item.getModelObject()
        item.add(new CommentPanel("commentPanel", bookService.initializeComment(comment)))
      }
    })

  }

}