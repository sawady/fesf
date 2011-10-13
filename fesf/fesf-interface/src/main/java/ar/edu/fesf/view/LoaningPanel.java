package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.LoaningService;

public class LoaningPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    public LoaningPanel(final String id, final Book book) {
        super(id);
        this.initialize(book);
    }

    private void initialize(final Book book) {

    }

    /* Accessors */
    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
