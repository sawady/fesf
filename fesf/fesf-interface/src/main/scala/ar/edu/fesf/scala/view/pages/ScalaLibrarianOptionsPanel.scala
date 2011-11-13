package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.panel.Panel
import ar.edu.fesf.scala.view.AjaxNamedSimpleCallback
import org.apache.wicket.markup.html.list.ListView
import java.util.List
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.basic.Label

class ScalaLibrarianOptionsPanel(
  id: String,
  callbacks: List[AjaxNamedSimpleCallback])
  extends Panel(id) {

  this.initialize()
  private def initialize() = {
    this.add(new ListView[AjaxNamedSimpleCallback]("links", callbacks) {
      override def populateItem(item: ListItem[AjaxNamedSimpleCallback]) = {
        val callback = item.getModelObject()
        item.add(new AjaxFallbackLink("link") {
          override def onClick(target: AjaxRequestTarget) = {
            callback(target)
          }
        }.add(new Label("linkText", callback.getName())))
      }
    })
  }

}