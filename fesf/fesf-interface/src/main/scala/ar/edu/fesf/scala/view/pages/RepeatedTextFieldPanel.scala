package ar.edu.fesf.scala.view.pages
import org.apache.wicket.markup.html.form.FormComponentPanel
import java.util.List
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.model.IModel
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.Component
import scala.collection.JavaConversions._

class RepeatedTextFieldPanel(
  id: String,
  list: List[String],
  f_textField: (String, IModel[String]) => TextField[String])
  extends FormComponentPanel[List[String]](id) {

  this.initialize()
  private def initialize() = {

    this.setOutputMarkupId(true)

    if (list.isEmpty()) {
      list.add("") // First element on the list
    }

    val container = new WebMarkupContainer("toUpdate")
    container.setOutputMarkupId(true)

    val listview = new ListView[String]("inputs", list) {
      override def populateItem(item: ListItem[String]) = {
        item.add(f_textField("input", item.getModel()))
      }
    }.setReuseItems(true)

    container.add(listview.setOutputMarkupId(true))
    container.add(new AjaxFallbackLink("addMore") {
      override def onClick(target: AjaxRequestTarget) = {
        list.add("")
        target.add(container)
      }
    }.setOutputMarkupId(true))

    this.add(container)
  }

}