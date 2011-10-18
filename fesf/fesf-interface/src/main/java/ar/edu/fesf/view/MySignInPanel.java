package ar.edu.fesf.view;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;

import ar.edu.fesf.controllers.IAjaxCallback;

public class MySignInPanel extends SignInPanel {

    private static final long serialVersionUID = 1L;

    private IAjaxCallback<?> succeedCallback;

    public MySignInPanel(final String id, final IAjaxCallback<?> succeedCallback) {
        super(id);
        this.setSucceedCallback(succeedCallback);
    }

    @Override
    protected void onSignInSucceeded() {
        // If login has been called because the user was not yet
        // logged in, than continue to the original destination,
        // otherwise to the Home page
        if (!this.continueToOriginalDestination()) {
            this.setResponsePage(this.getApplication().getHomePage());
        }
    }

    public void setSucceedCallback(final IAjaxCallback<?> succeedCallback) {
        this.succeedCallback = succeedCallback;
    }

    public IAjaxCallback<?> getSucceedCallback() {
        return this.succeedCallback;
    }

}
