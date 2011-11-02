package ar.edu.fesf.view.pages.loaning;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.LoaningService;
import ar.edu.fesf.view.pages.generic.BackingPanel;

public class LoaneesPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    /* Methods */

    public LoaneesPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(this.createNavigationPanel());
    }

    private LoaneesNavigationPanel createNavigationPanel() {
        LoaneesNavigationPanel loaneesNavigationPanel = new LoaneesNavigationPanel("content",
                this.changeToLoaneeInfoPanel());
        loaneesNavigationPanel.setOutputMarkupId(true);
        return loaneesNavigationPanel;
    }

    public IAjaxCallback<Person> changeToLoaneeInfoPanel() {
        return new AjaxReplacePanel<Person>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Person person) {
                BackingPanel backingPanel = new BackingPanel("content") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Panel getContentPanel(final String id) {
                        return new LoaneeInfoPanel(id, person);
                    }

                    @Override
                    public void callback(final AjaxRequestTarget target) {
                        LoaneesPanel.this.changeToNavigationPanel().callback(target, null);
                    }

                };
                backingPanel.setOutputMarkupId(true);
                return backingPanel;
            }

        };
    }

    public IAjaxCallback<List<Person>> changeToNavigationPanel() {
        return new AjaxReplacePanel<List<Person>>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final List<Person> list) {
                return LoaneesPanel.this.createNavigationPanel();
            }

        };
    }

    /* Accessors */

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
