package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.model.Book
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.model.Model
import org.apache.commons.lang.StringUtils
import org.apache.wicket.model.PropertyModel

class ScalaBookInfoMiniPanel(id: String, 
    book: Book, 
    f_callback: (AjaxRequestTarget, Book) => Unit)
  extends Panel(id) {

  this.initialize()

  private def initialize() = {
    this.add(new AjaxFallbackLink[String]("link") {
      override def onClick(target: AjaxRequestTarget) {
        f_callback(target, book);
      }
    });
    this.add(new Label("title", book.getTitle()));
    this.add(new Label("authorNames", StringUtils.join(this.book.getAuthors(), ',')));
    this.add(new Label("categoryNames", StringUtils.join(this.book.getCategories(), ',')));
  }

}