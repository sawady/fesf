package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.model.Book
import ar.edu.fesf.wicket.application.SecuritySession

class BorrowItPanel(id: String,
                    book: Book) extends Panel(id) {

  this.initialize()

  private def initialize() = {
    this.setVisible(this.getSession().asInstanceOf[SecuritySession].getPerson() != null)
  }

}