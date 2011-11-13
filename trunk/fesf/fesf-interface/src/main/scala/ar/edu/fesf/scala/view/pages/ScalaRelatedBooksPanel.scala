package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.model.Book
import java.util.List
import ar.edu.fesf.controllers.IAjaxCallback

class ScalaRelatedBooksPanel(
  id: String,
  relatedBooks: List[Book],
  relatedBookCallback: IAjaxCallback[Book]) extends Panel(id) {

  this.initialize()
  private def initialize() = {
    this.setVisible(!relatedBooks.isEmpty())
    this.add(new ScalaHorizontalBookPanel("list", relatedBooks, relatedBookCallback))
  }

}