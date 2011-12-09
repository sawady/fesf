package ar.edu.fesf.scala.view

import org.apache.wicket.markup.html.WebComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.markup.ComponentTag
import org.apache.wicket.model.Model

class StaticImage(id: String, model: IModel[String]) extends WebComponent(id, model) {

  def this(id: String, src: String) = this(id, new Model(src))

  override def onComponentTag(tag: ComponentTag) = {
    super.onComponentTag(tag);
    checkComponentTag(tag, "img");
    tag.put("src", getDefaultModelObjectAsString());
  }

}