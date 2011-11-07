package ar.edu.fesf.view.pages.loaning;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.services.LoaningService;
import ar.edu.fesf.view.pages.generic.GenericFormPanel;

public class LoaningFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    public LoaningFormPanel(final String id, final Book book, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.initialize(book, ajaxCallback);
    }

    private void initialize(final Book book, final IAjaxCallback<Book> ajaxCallback) {

        this.setOutputMarkupId(true);
        this.add(new GenericFormPanel<Loan>("form") {

            private static final long serialVersionUID = 1L;

            @Override
            public PanelServiceToForm<Loan> getFieldsPanel(final String id) {
                return new LoanFormFieldsPanel(id, book, new Loan(), ajaxCallback);
            }

        });
    }

    /* Accessors */
    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
