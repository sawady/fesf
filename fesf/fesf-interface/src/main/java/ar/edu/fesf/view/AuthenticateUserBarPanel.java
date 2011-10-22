package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

public class AuthenticateUserBarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AuthenticateUserBarPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(new AjaxFallbackLink<String>("signIn") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                throw new UnsupportedOperationException();
            }

        });
        this.add(new AjaxFallbackLink<String>("signUp") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                throw new UnsupportedOperationException();
            }

        });
    }
}
