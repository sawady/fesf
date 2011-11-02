package ar.edu.fesf.view.pages.loaning;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.LoaningService;

public class LoaneesNavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.loan")
    private LoaningService loaningService;

    /* Methods */

    public LoaneesNavigationPanel(final String id, final IAjaxCallback<Person> ajaxCallback) {
        super(id);
        this.initialize(ajaxCallback);
    }

    public void initialize(final IAjaxCallback<Person> ajaxCallback) {
        this.add(new ListView<Person>("loaneeList", this.getLoaningService().getLoanees()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Person> item) {
                item.add(new OneLineLoaneePanel("oneLineLoaneePanel", item.getModelObject(), ajaxCallback));
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
