package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.scala.view.ReplaceablePanel
import ar.edu.fesf.model.Book
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Person
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.markup.html.basic.Label
import org.apache.commons.lang.StringUtils
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import ar.edu.fesf.model.UserFeedbackManager
import ar.edu.fesf.services.UserFeedbackService

class ScalaBookInfoPanel(id: String,
  book: Book,
  borrowCallback: IAjaxCallback[Book],
  relatedBookCallback: IAjaxCallback[Book],
  cannotBorrowCallback: IAjaxCallback[Person]) extends ReplaceablePanel(id, new CompoundPropertyModel(book)) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  @SpringBean
  @BeanProperty
  var userFeedbackService: UserFeedbackService = _

  this.initialize()

  private def initialize() = {
    this.add(new Label("title"))
    this.add(new Label("authorNames", StringUtils.join(book.getAuthors(), ',')))
    this.add(new Label("publisher.name"))
    this.add(new Label("categoryNames", StringUtils.join(book.getCategories(), ',')))
    this.add(new Label("description"))
    this.add(new Label("isbn.value"))
    this.add(new Label("countOfAvailableCopies", book.getCountOfAvailableCopies().toString()))
    this.add(new BorrowItPanel("borrowIt", book, borrowCallback, cannotBorrowCallback))
    val relatedBooks = this.getBookService().relatedBooks(book.getId(), 10)
    this.add(new ScalaHorizontalBookPanel("relatedBooks", relatedBooks, relatedBookCallback))

    val userFeedback = bookService.getUserFeedback(book)
    this.add(new Label("calification", userFeedback.getAvgCalification().toString()))
    this.add(new CommentListPanel("comments", userFeedbackService.getComments(userFeedback, 10)))
  }

}