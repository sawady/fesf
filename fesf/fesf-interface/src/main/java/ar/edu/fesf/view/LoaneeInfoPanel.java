package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxNamedAction;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.LoaningService;

public class LoaneeInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    /* Methods */

    public LoaneeInfoPanel(final String id, final Person loanee) {
        super(id, new CompoundPropertyModel<Person>(loanee));
        this.initialize(loanee);
    }

    private void initialize(final Person loanee) {
        this.add(new PersonInfoPanel("loanee", loanee));
        this.add(new ListView<Loan>("loans", loanee.getCurrentLoans()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Loan> item) {
                Loan loan = (Loan) item.getDefaultModelObject();
                item.add(new Label("bookTitle", loan.getBook().getTitle()));
                item.add(new Label("loanDate", loan.getDate().toString("dd-MMMM-yyyy")));
                item.add(new Label("agreedDate", loan.getAgreedReturnDate().toString("dd-MMMM-yyyy")));
                item.add(new ActionsPanel<Loan>("actions", loan, LoaneeInfoPanel.this.actions()));
            }
        });
    }

    public List<AjaxNamedAction<Loan>> actions() {
        List<AjaxNamedAction<Loan>> actions = new ArrayList<AjaxNamedAction<Loan>>();
        actions.add(new AjaxNamedAction<Loan>("return", this.getReturnAction()));
        return actions;
    }

    private IAjaxCallback<Loan> getReturnAction() {
        return new IAjaxCallback<Loan>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Loan loan) {
                LoaneeInfoPanel.this.getLoaningService().registerBookReturn(loan);
            }
        };
    }

    /* Accessors */

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }
}
