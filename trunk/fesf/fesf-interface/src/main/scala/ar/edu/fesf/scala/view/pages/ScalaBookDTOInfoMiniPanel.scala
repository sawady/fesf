package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.dtos.NewBookDTO
import ar.edu.fesf.controllers.IAjaxCallback
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.commons.lang.StringUtils
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.scala.view.StaticImage

class ScalaBookDTOInfoMiniPanel(id: String,
  book: NewBookDTO,
  callback: IAjaxCallback[NewBookDTO])
  extends Panel(id) {

  this.initialize()

  private def initialize() = {
    this.add(new AjaxFallbackLink[String]("link") {
      override def onClick(target: AjaxRequestTarget) {
        callback(target, book)
      }
    }.add(new StaticImage("bookInfoImage", book.getImageLink())))
    this.add(new Label("title", book.getTitle()));
    this.add(new Label("authorNames", StringUtils.join(book.getAuthors(), ',')))
    this.add(new Label("categoryNames", StringUtils.join(book.getCategories(), ',')))
  }

}