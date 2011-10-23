package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;

public class HomeUserbarPanel extends Panel {

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
        this.add(this.myAuthenticateUserBarPanel());
    }

    public Panel myAuthenticatedUserBarPanel() {
        return new AuthenticatedUserBarPanel("authentication") {

            private static final long serialVersionUID = 1L;

            @Override
            public void signOutCallback(final AjaxRequestTarget target) {
                // TODO debería cancelar la session del usuario
                Panel aNewPanel = HomeUserbarPanel.this.myAuthenticateUserBarPanel();
                HomeUserbarPanel.this.replace(aNewPanel);
                target.add(aNewPanel);
            }

            @Override
            public void profileCallback(final AjaxRequestTarget target) {
                // TODO profile callback
            }

            @Override
            public void loansCallback(final AjaxRequestTarget target) {
                // TODO loans callback
            }

        };

    }

    public Panel myAuthenticateUserBarPanel() {
        return new AuthenticateUserBarPanel("authentication") {

            private static final long serialVersionUID = 1L;

            @Override
            public void signUpCallback(final AjaxRequestTarget target) {
                // TODO signUp callback
            }

            @Override
            public void signInCallback(final AjaxRequestTarget target) {
                // TODO en realidad debería loguear
                Panel aNewPanel = HomeUserbarPanel.this.myAuthenticatedUserBarPanel();
                HomeUserbarPanel.this.replace(aNewPanel);
                target.add(aNewPanel);
            }
        };

    }

    public void setHomeLinkCallback(final IAjaxCallback<?> homeLinkCallback) {
        this.homeLinkCallback = homeLinkCallback;
    }

    public IAjaxCallback<?> getHomeLinkCallback() {
        return this.homeLinkCallback;
    }

}
