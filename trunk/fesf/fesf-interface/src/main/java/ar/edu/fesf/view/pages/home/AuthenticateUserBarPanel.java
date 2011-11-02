package ar.edu.fesf.view.pages.home;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.view.pages.signin.SignIn;

public abstract class AuthenticateUserBarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AuthenticateUserBarPanel(final String id) {
        super(id);
        this.setOutputMarkupId(true);
        this.initialize();
    }

    private void initialize() {

        this.add(new BookmarkablePageLink<String>("signIn", SignIn.class));

        this.add(new AjaxFallbackLink<String>("signUp") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                AuthenticateUserBarPanel.this.signUpCallback(target);
            }

        });
    }

    public abstract void signUpCallback(AjaxRequestTarget target);
}
