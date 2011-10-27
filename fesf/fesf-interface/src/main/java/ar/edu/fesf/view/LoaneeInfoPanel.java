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
import ar.edu.fesf.services.PersonService;

public class LoaneeInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Person loanee;

    public Person getLoanee() {
        return this.loanee;
    }

    public void setLoanee(final Person loanee) {
        this.loanee = loanee;
    }

    @SpringBean(name = "service.person")
    private PersonService personService;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    /* Methods */

    public LoaneeInfoPanel(final String id, final Person loanee) {
        super(id, new CompoundPropertyModel<Person>(loanee));
        this.loanee = loanee;
        this.initialize(loanee);
    }

    private void initialize(final Person loanee) {
        this.setOutputMarkupId(true);
        this.add(new PersonInfoPanel("loanee", loanee));
        this.add(this.getLoanList("loans"));
    }

    private ListView<Loan> getLoanList(final String id) {
        ListView<Loan> newListView = new ListView<Loan>(id, this.getPersonService().getCurrentLoans(this.getLoanee())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Loan> item) {
                Loan loan = (Loan) item.getDefaultModelObject();
                Loan loanDB = LoaneeInfoPanel.this.getLoaningService().initializeLoanInfo(loan);
                item.setModel(new CompoundPropertyModel<Loan>(loanDB));
                item.add(new Label("bookCopy.book.title"));
                item.add(new Label("loanDate", loan.getDate().toString("dd-MMMM-yyyy")));
                item.add(new Label("agreedDate", loan.getAgreedReturnDate().toString("dd-MMMM-yyyy")));
                item.add(new ActionsPanel<Loan>("actions", loanDB, LoaneeInfoPanel.this.actions()));
            }
        };
        newListView.setOutputMarkupId(true);
        return newListView;
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
                ListView<Loan> newLoanList = LoaneeInfoPanel.this.getLoanList("loans");
                LoaneeInfoPanel.this.replace(newLoanList);
                target.add(LoaneeInfoPanel.this);
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

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

}
