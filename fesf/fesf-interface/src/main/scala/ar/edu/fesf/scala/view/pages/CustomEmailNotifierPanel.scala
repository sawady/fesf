package ar.edu.fesf.scala.view.pages

import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.ReplaceablePanel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction
import org.apache.wicket.markup.html.basic.Label
import ar.edu.fesf.model.Book
import java.util.List
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.EmailService
import ar.edu.fesf.scala.view.application.SecuritySession

@AuthorizeAction(action = "RENDER", roles = Array("ROLE_USER"))
class CustomEmailNotifierPanel(id: String, books: List[Book]) extends Panel(id) with ReplaceablePanel {

  @SpringBean
  @BeanProperty
  var emailService: EmailService = _

  this.initialize()
  private def initialize() = {
    this.add(new AjaxFallbackLink("notify") {
      override def onClick(target: AjaxRequestTarget) = {
        this.setEnabled(false);
        this.addOrReplace(new Label("notifyText", "Email sended").setOutputMarkupId(true));
        target.add(this);
        CustomEmailNotifierPanel.this.notifyBooks();
      }
    }.add(new Label("notifyText", "Send these by email").setOutputMarkupId(true)).setOutputMarkupId(true))
  }

  private def notifyBooks() = {
    this.emailService.sendEmailCustom(this.books, this.getSession().asInstanceOf[SecuritySession].getPerson())
  }

}