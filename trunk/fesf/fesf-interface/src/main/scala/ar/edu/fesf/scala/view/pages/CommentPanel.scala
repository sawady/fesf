package ar.edu.fesf.scala.view.pages

import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.model.Comment
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.markup.html.basic.Label
import ar.edu.fesf.dtos.CommentDTO

class CommentPanel(id: String, comment: CommentDTO) extends Panel(id, new CompoundPropertyModel(comment)) {

  this.initialize()

  //TODO revisar esto que puede estar mal
  private def initialize() = {
    add(new Label("person"))
    add(new Label("calification"))
    add(new Label("body"))
  }

}