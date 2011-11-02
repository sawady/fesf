package ar.edu.fesf.view.pages.home;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.wicket.application.SecuritySession;

public abstract class HomeUserbarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private IAjaxCallback<?> homeLinkCallback;

    public HomeUserbarPanel(final String id, final IAjaxCallback<?> homeLinkCallback) {
        super(id);
        this.setHomeLinkCallback(homeLinkCallback);
        this.initialize();
    }

    private void initialize() {
        this.add(new AjaxFallbackLink<String>("homeLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                HomeUserbarPanel.this.getHomeLinkCallback().callback(target, null);
            }

        });
        this.add(this.getBookSearchPanel("searchbar"));

        if (((SecuritySession) this.getSession()).signedIn()) {
            this.add(this.myAuthenticatedUserBarPanel());
        } else {
            this.add(this.myAuthenticateUserBarPanel());
        }

    }

    public Panel myAuthenticatedUserBarPanel() {
        return new AuthenticatedUserBarPanel("authentication") {

            private static final long serialVersionUID = 1L;

            @Override
            public void signOutCallback(final AjaxRequestTarget target) {
                ((SecuritySession) this.getSession()).signOutPerson();
                Panel aNewPanel = HomeUserbarPanel.this.myAuthenticateUserBarPanel();
                HomeUserbarPanel.this.replace(aNewPanel);
                target.add(aNewPanel);
            }

            @Override
            public void profileCallback(final AjaxRequestTarget target) {
                HomeUserbarPanel.this.profileCallback(target);
            }

            @Override
            public void loansCallback(final AjaxRequestTarget target) {
                HomeUserbarPanel.this.loansCallback(target);
            }

        };

    }

    public abstract Panel getBookSearchPanel(String id);

    public abstract void profileCallback(AjaxRequestTarget target);

    public abstract void loansCallback(AjaxRequestTarget target);

    public abstract void signUpCallback(AjaxRequestTarget target);

    public abstract void successfullSignInCallback(AjaxRequestTarget target);

    public Panel myAuthenticateUserBarPanel() {
        return new AuthenticateUserBarPanel("authentication") {

            private static final long serialVersionUID = 1L;

            @Override
            public void signUpCallback(final AjaxRequestTarget target) {
                HomeUserbarPanel.this.signUpCallback(target);
            }

        };

    }

    /* ACCESSORS */

    public void setHomeLinkCallback(final IAjaxCallback<?> homeLinkCallback) {
        this.homeLinkCallback = homeLinkCallback;
    }

    public IAjaxCallback<?> getHomeLinkCallback() {
        return this.homeLinkCallback;
    }

}
