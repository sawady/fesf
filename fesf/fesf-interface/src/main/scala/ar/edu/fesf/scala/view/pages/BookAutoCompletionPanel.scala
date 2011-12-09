package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.ScalaContainerPanel
import ar.edu.fesf.dtos.NewBookDTO
import ar.edu.fesf.controllers.IAjaxCallback
import java.util.List
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty
import ar.edu.fesf.services.BookService
import ar.edu.fesf.model.Book
import ar.edu.fesf.model.Person
import ar.edu.fesf.view.pages.persons.PersonInfoPanel
import org.apache.wicket.markup.html.form.Form
import ar.edu.fesf.builders.BookBuilder
import java.util.ArrayList
import scala.collection.JavaConversions._
import ar.edu.fesf.model.ISBN
import ar.edu.fesf.model.Publisher
import ar.edu.fesf.controllers.AjaxReplacePanel
import ar.edu.fesf.scala.view.ScalaAjaxReplacePanel
import ar.edu.fesf.scala.view.ToLazyPanel

class BookAutoCompletionPanel(id: String,
  moreInfoCallback: IAjaxCallback[Book]) extends ScalaContainerPanel(id) {

  override val CONTENT_ID = "CONTENT"

  @SpringBean
  @BeanProperty
  var bookService: BookService = _

  /* initialization */
  this.initialize()

  private def initialize() = {
    this.add(new GBSearchPanel("GBSearch", this.changeResults()))
    this.add(new WebMarkupContainer("GBResults").setOutputMarkupId(true))
    this.add(this.formPanel(CONTENT_ID, new NewBookDTO()))
  }

  private def formPanel(id: String, bookDTO: NewBookDTO): ScalaGenericFormPanel[NewBookDTO] = {
    new ScalaGenericFormPanel(CONTENT_ID, new ScalaBookNewFormFieldsPanel(_, moreInfoCallback, bookDTO))
      .setOutputMarkupId(true).asInstanceOf[ScalaGenericFormPanel[NewBookDTO]]
  }

  private def changeFormPanel(): IAjaxCallback[NewBookDTO] = {
    this.changeContent((id: String) => (bookDTO: NewBookDTO) => this.formPanel(id, bookDTO))
  }

  private def changeResults(): IAjaxCallback[List[NewBookDTO]] = {
    val panel = (list: List[NewBookDTO]) => ToLazyPanel("GBResults", (innerID: String) =>
      new ScalaHorizontalBookDTOPanel(innerID, list, changeFormPanel())).setOutputMarkupId(true).asInstanceOf[Panel]
    new ScalaAjaxReplacePanel(this, panel)
  }

}