package ar.edu.fesf.view.pages.loaning;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;

public class OneLineLoaneePanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Person loanee;

    /* Methods */

    public OneLineLoaneePanel(final String id, final Person loanee, final IAjaxCallback<Person> callback) {
        super(id);
        this.loanee = loanee;
        this.initialize(callback);
    }

    private void initialize(final IAjaxCallback<Person> callback) {
        AjaxFallbackLink<Person> ajaxFallbackLink = new AjaxFallbackLink<Person>("loaneeLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                callback.apply(target, OneLineLoaneePanel.this.getLoanee());
            }
        };
        ajaxFallbackLink.add(new Label("loaneeName", this.getLoanee().getName()));
        this.add(ajaxFallbackLink);
    }

    /* Accessors */

    public Person getLoanee() {
        return this.loanee;
    }

    public void setLoanee(final Person loanee) {
        this.loanee = loanee;
    }

}
