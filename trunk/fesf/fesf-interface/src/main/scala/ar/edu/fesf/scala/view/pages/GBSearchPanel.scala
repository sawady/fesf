package ar.edu.fesf.scala.view.pages

import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.dtos.NewBookDTO
import org.apache.wicket.markup.html.panel.Panel
import java.util.List

class GBSearchPanel(
  id: String,
  callback: IAjaxCallback[List[NewBookDTO]]) extends Panel(id) {

  this.add(new GBSearch("form", callback))

}