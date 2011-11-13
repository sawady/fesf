package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import java.util.List
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book

class ScalaBookSearchPanel(
  id: String,
  callback: IAjaxCallback[List[Book]]) extends Panel(id) {

  this.initialize()
  private def initialize() = {
    this.add(new ScalaBookSearchForm("form", callback));
  }

}