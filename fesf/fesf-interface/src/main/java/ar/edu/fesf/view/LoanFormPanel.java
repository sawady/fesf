package ar.edu.fesf.view;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.LoanForm;
import ar.edu.fesf.model.Loan;

public class LoanFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LoanFormPanel(final String id, final Loan loan, final IAjaxCallback<Form<Loan>> iAjaxCallback) {
        super(id);
        this.initialize(loan, iAjaxCallback);
    }

    private void initialize(final Loan loan, final IAjaxCallback<Form<Loan>> iAjaxCallback) {
        this.add(new LoanForm("form", loan, iAjaxCallback));
    }
}
