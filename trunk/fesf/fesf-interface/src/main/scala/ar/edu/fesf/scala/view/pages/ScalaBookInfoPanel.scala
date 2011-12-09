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
import ar.edu.fesf.scala.view.ToAjaxSimpleCallback
import ar.edu.fesf.dtos.CommentDTO
import java.util.List
import org.apache.wicket.extensions.yui.calendar.DatePicker
import ar.edu.fesf.scala.view.AjaxSimpleReplacePanel
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.image.Image
import ar.edu.fesf.scala.view.StaticImage

class ScalaBookInfoPanel(
  id: String,
  book: Book,
  showMoreInfoCallback: IAjaxCallback[Book],
  cannotBorrowCallback: IAjaxCallback[Person])
  extends Panel(id, new CompoundPropertyModel(book)) with ReplaceablePanel {

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
    this.add(new BorrowItPanel("borrowIt", book, this.borrowCallback(), cannotBorrowCallback))
    this.add(new StaticImage("bookImage", book.getImagepath()))
    val relatedBooks = this.getBookService().relatedBooks(book.getId(), 10)
    this.add(new ScalaRelatedBooksPanel("relatedBooks", relatedBooks, showMoreInfoCallback))
    this.addUserFeedbackInfo()
  }

  private def borrowCallback(): IAjaxSimpleCallback = {
    return new AjaxSimpleReplacePanel(this,
      new ScalaGenericFormPanel("borrowIt", new BorrowItFormFieldsPanel(_: String, book, showMoreInfoCallback)))
  }

  private def addUserFeedbackInfo() {
    val userFeedback = bookService.getUserFeedback(book)
    this.addOrReplace(getCalificationLabel(userFeedback.getAvgCalification()))
    this.addOrReplace(getCommentList(userFeedbackService.getComments(userFeedback, 10)))
  }

  private def submitCommentCallback(target: AjaxRequestTarget) = {
    this.addUserFeedbackInfo()
    target.add(this);
  }

  private def getCalificationLabel(calif: Int): Label =
    new Label("calification", calif.toString())

  private def getCommentList(comments: List[CommentDTO]): CommentListPanel =
    new CommentListPanel("comments", book, comments, ToAjaxSimpleCallback(submitCommentCallback))

}