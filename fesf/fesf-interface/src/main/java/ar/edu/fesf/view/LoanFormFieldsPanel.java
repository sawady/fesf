package ar.edu.fesf.view;

import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import org.joda.time.DateTime;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.security.SecuritySession;
import ar.edu.fesf.services.LoaningService;

public class LoanFormFieldsPanel extends PanelServiceToForm<Loan> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private LoaningService loaningService;

    private IAjaxCallback<Loan> ajaxCallback;

    private Loan loan;

    private Book book;

    public LoanFormFieldsPanel(final String id, final Book book, final Loan loan, final IAjaxCallback<Loan> ajaxCallback) {
        super(id);
        this.book = book;
        this.loan = loan;
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        RequiredTextField<DateTime> requiredTextField = new RequiredTextField<DateTime>("agreedReturnDate") {
            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            public <C> IConverter<C> getConverter(final Class<C> type) {
                return (IConverter<C>) new IConverter<DateTime>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public DateTime convertToObject(final String value, final Locale locale) {
                        return new DateTime().plusDays(20);
                    }

                    @Override
                    public String convertToString(final DateTime value, final Locale locale) {
                        return value.toString("dd/mm/yyyy", locale);
                    }

                };

            };
        };

        requiredTextField.setType(DateTime.class);
        this.add(requiredTextField);
    }

    @Override
    public Loan getObject() {
        return this.getLoan();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<Loan> form) {
        SecuritySession myWebSession = (SecuritySession) this.getSession();
        this.getLoaningService().registerLoan(myWebSession.getPerson(), this.getLoan(), this.getBook());
        this.getAjaxCallback().callback(target, this.getLoan());
    }

    /* Accessors */

    public void setAjaxCallback(final IAjaxCallback<Loan> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

    public IAjaxCallback<Loan> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setLoan(final Loan loan) {
        this.loan = loan;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

}
