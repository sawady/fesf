package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Loan;
import ar.edu.fesf.services.LoaningService;

public class LoanForm extends Form<Loan> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoanForm(final String id, final Loan loan, final IAjaxCallback<Form<Loan>> iAjaxCallback) {
        super(id, new CompoundPropertyModel<Loan>(loan));
        this.initialize(iAjaxCallback);
    }

    private void initialize(final IAjaxCallback<Form<Loan>> iAjaxCallback) {
        RequiredTextField<String> titleField = new RequiredTextField<String>("title");
        this.add(titleField);
        final FeedbackPanel titleFeedback = new FeedbackPanel("titleFeedback", new ComponentFeedbackMessageFilter(
                titleField));
        titleFeedback.setOutputMarkupId(true);
        this.add(titleFeedback);

        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(titleFeedback);
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                // TODO
                // LoanForm.this.getBookService().save((Book) form.getModelObject());
                // iAjaxCallback.callback(target, (Form<Book>) form);
            }

        });
    }

}
