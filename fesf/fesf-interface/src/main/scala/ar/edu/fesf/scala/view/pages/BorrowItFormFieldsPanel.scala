package ar.edu.fesf.scala.view.pages
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.services.dtos.BorrowItDTO
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.AjaxRequestTarget
import ar.edu.fesf.controllers.IAjaxCallback
import ar.edu.fesf.model.Book
import ar.edu.fesf.services.LoaningService
import scala.reflect.BeanProperty
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.scala.view.application.SecuritySession
import org.apache.wicket.extensions.yui.calendar.DatePicker
import ar.edu.fesf.scala.view.IAjaxSimpleCallback
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.validation.validator.DateValidator
import java.util.Date
import org.joda.time.DateTime

@SerialVersionUID(1L)
class BorrowItFormFieldsPanel(
  id: String,
  book: Book,
  submitCallback: IAjaxCallback[Book]) extends PanelServiceToForm[BorrowItDTO](id) {

  @SpringBean
  @BeanProperty
  var loaningService: LoaningService = _

  val borrowItDTO = new BorrowItDTO()

  this.initialize()
  private def initialize() = {
    val dateField = new RequiredTextField("agreedReturnDate", classOf[Date]).add(
      DateValidator.range(new DateTime().toDate(), new DateTime().plusDays(60).toDate(), "MM/dd/yy"))
    add(dateField.add(new DatePicker()));
  }

  override def getObject(): BorrowItDTO = this.borrowItDTO

  override def doSubmit(target: AjaxRequestTarget, form: Form[BorrowItDTO]) = {
    val myWebSession = this.getSession().asInstanceOf[SecuritySession];
    this.getLoaningService().registerLoan(myWebSession.getPerson(), this.borrowItDTO, book);
    submitCallback(target, book);
  }

}