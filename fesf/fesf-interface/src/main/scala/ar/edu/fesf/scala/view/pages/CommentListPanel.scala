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
import ar.edu.fesf.services.dtos.CommentDTO
import ar.edu.fesf.scala.view.ToGenericFormPanel
import ar.edu.fesf.wicket.application.SecuritySession
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.WebMarkupContainer
import ar.edu.fesf.scala.view.ToAjaxSimpleCallback
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy
import org.apache.wicket.Component
import ar.edu.fesf.model.Role
import ar.edu.fesf.scala.view.ReplaceablePanel
import ar.edu.fesf.scala.view.IAjaxSimpleCallback

class CommentListPanel(id: String, book: Book,
                       comments: List[CommentDTO],
                       callback: IAjaxSimpleCallback) extends ReplaceablePanel(id) {

  initialize()

  def initialize() = {
    this.add(new ListView[CommentDTO]("commentList", comments) {
      @Override
      def populateItem(item: ListItem[CommentDTO]) {
        val comment = item.getModelObject()
        item.add(new CommentPanel("commentPanel", comment))
      }
    })
    val commentForm = ToGenericFormPanel("commentForm", new CommentFieldsPanel(_: String, book,
      this.getSession().asInstanceOf[SecuritySession].getPerson(),
      callback)).setOutputMarkupId(true)
    MetaDataRoleAuthorizationStrategy.authorize(commentForm, Component.RENDER, Role.USER.toString());
    this.add(commentForm)
  }

}