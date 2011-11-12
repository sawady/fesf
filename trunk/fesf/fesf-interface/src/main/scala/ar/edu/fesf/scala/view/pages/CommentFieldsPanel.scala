package ar.edu.fesf.scala.view.pages

import ar.edu.fesf.controllers.PanelServiceToForm
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.BookService
import org.apache.wicket.markup.html.form.TextArea
import ar.edu.fesf.model.Person
import ar.edu.fesf.model.Book
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.services.dtos.CommentDTO
import org.apache.wicket.markup.html.form.RequiredTextField
import scala.reflect.BeanProperty
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.validation.validator.MinimumValidator
import org.apache.wicket.validation.validator.MaximumValidator
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import org.apache.wicket.authorization.strategies.action.ActionAuthorizationStrategy
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction

class CommentFieldsPanel(id: String,
                         book: Book,
                         person: Person,
                         submitCommentCallback: IAjaxSimpleCallback) extends PanelServiceToForm[CommentDTO](id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  val commentDTO: CommentDTO = new CommentDTO()

  this.initialize()
  def initialize() = {

    add(new TextArea("body").setRequired(true))
    add(new RequiredTextField[Int]("calification", classOf[Int]).add(new MinimumValidator[Integer](0)).add(new MaximumValidator[Integer](10)))
  }

  def getObject(): CommentDTO = commentDTO

  def doSubmit(target: AjaxRequestTarget, form: Form[CommentDTO]) = {
    bookService.registerComment(commentDTO, book, person)
    submitCommentCallback(target)
  }

}