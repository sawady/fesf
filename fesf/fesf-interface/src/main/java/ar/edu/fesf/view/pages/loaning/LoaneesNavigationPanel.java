package ar.edu.fesf.view.pages.loaning;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.EmailService;
import ar.edu.fesf.services.LoaningService;

public class LoaneesNavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private LoaningService loaningService;

    @SpringBean
    private EmailService emailService;

    private List<Person> bastardLoanees;

    /* Methods */

    public LoaneesNavigationPanel(final String id, final IAjaxCallback<Person> ajaxCallback) {
        super(id);
        this.initialize(ajaxCallback);
    }

    public void initialize(final IAjaxCallback<Person> ajaxCallback) {
        this.bastardLoanees = this.getLoaningService().findBastardLoanees();

        this.add(new AjaxFallbackLink<String>("notifyBastards") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                this.setEnabled(false);
                this.addOrReplace(new Label("notifyText", "Bastards notified").setOutputMarkupId(true));
                target.add(this);
                LoaneesNavigationPanel.this.notifyBastards();
            }

        }.add(new Label("notifyText", "Notify the bastards").setOutputMarkupId(true)).setOutputMarkupId(true)
                .setVisible(!this.bastardLoanees.isEmpty()));

        this.add(new ListView<Person>("loaneeList", this.bastardLoanees) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Person> item) {
                item.add(new OneLineLoaneePanel("oneLineLoaneePanel", item.getModelObject(), ajaxCallback));
            }
        });
    }

    public void notifyBastards() {
        this.emailService.sendEmailToLoanees(this.bastardLoanees);
    }

    /* Accessors */

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
