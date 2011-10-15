package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.services.LoaningService;
import ar.edu.fesf.view.LoanFormFieldsPanel;

public class ServiceToLoanForm implements ServiceToForm<Loan> {

    private LoaningService loaningService;

    private IAjaxCallback<Book> ajaxCallback;

    private Loan loan;

    private Book book;

    /* Methods */

    public ServiceToLoanForm(final Book book, final Loan loan, final IAjaxCallback<Book> ajaxCallback,
            final LoaningService loaningService) {
        this.book = book;
        this.loan = loan;
        this.ajaxCallback = ajaxCallback;
        this.loaningService = loaningService;
    }

    @Override
    public Loan getObject() {
        return this.getLoan();
    }

    @Override
    public IAjaxCallback<Loan> doSubmitCallback(final AjaxRequestTarget target, final Form<Loan> form) {
        return new IAjaxCallback<Loan>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Loan loan) {
                ServiceToLoanForm.this.getLoaningService().registerLoan(loan, ServiceToLoanForm.this.getBook());
                ServiceToLoanForm.this.getAjaxCallback().callback(target, ServiceToLoanForm.this.getBook());
            }

        };
    }

    @Override
    public Panel getFieldsPanel(final String id) {
        LoanFormFieldsPanel loanFormFieldsPanel = new LoanFormFieldsPanel(id);
        loanFormFieldsPanel.setOutputMarkupId(true);
        return loanFormFieldsPanel;
    }

    /* Accessors */

    public void setLoan(final Loan loan) {
        this.loan = loan;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public IAjaxCallback<Book> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setAjaxCallback(final IAjaxCallback<Book> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

}
