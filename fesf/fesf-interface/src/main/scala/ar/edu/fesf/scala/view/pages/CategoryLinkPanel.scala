package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import org.apache.wicket.model.IModel
import ar.edu.fesf.model.Book
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.model.Category
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import java.util.List
import org.apache.wicket.markup.html.basic.Label

class CategoryLinkPanel(id: String,
  categoryModel: IModel[Category],
  f_resultsCallback: (AjaxRequestTarget, List[Book]) => Unit) extends Panel(id) {

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  this.initialize()

  private def initialize() = {
    val category = categoryModel.getObject()
    val link = new AjaxFallbackLink[Category]("link", categoryModel) {
      override def onClick(target: AjaxRequestTarget) = {
        f_resultsCallback(target, bookService.findByCategory(category))
      }
    }
    link.add(new Label("linkText", category.getName()))
    this.add(link)
  }

}