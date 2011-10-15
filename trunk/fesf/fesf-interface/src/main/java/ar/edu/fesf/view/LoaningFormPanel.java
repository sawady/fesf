package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.ServiceToLoanForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.services.LoaningService;

public class LoaningFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    public LoaningFormPanel(final String id, final Book book, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.initialize(book, ajaxCallback);
    }

    private void initialize(final Book book, final IAjaxCallback<Book> ajaxCallback) {
        this.add(new GenericFormPanel<Loan>("form", new ServiceToLoanForm(book, new Loan(), ajaxCallback, this
                .getLoaningService())));
    }

    /* Accessors */
    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
