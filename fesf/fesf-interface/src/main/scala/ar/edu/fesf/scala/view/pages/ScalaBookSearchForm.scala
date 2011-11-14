package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.form.Form
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.apache.wicket.ajax.AjaxRequestTarget
import java.util.List
import ar.edu.fesf.scala.view.application.SecuritySession

class ScalaBookSearchForm(
  id: String,
  callback: IAjaxCallback[List[Book]]) extends Form[ScalaBookSearchForm](id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  @BeanProperty
  var input: String = _

  this.initialize()
  private def initialize() = {
    this.add(new TextField[String]("input", new PropertyModel[String](this, "input")));
    this.add(new AjaxFallbackButton("submit", this) {
      override def onSubmit(target: AjaxRequestTarget, form: Form[_]) = {
        if (input != null) {
          if (getSession().asInstanceOf[SecuritySession].isLibrarianSignedIn()) {
            callback(target, bookService.bookSearchForLibrarian(input));
          } else {
            callback(target, bookService.bookSearch(input));
          }
        }
      }
      override def onError(target: AjaxRequestTarget, form: Form[_]) = {
        // No se requiere hacer nada
      }
    })
  }

}