package ar.edu.fesf.view;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.AuthenticationService;

public class WebSession extends AuthenticatedWebSession {

    @SpringBean(name = "service.authentication")
    private AuthenticationService authenticationService;

    private Person person;

    public WebSession(final Request request) {
        super(request);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean authenticate(final String username, final String password) {
        Person aPerson = this.getAuthenticationService().authenticate(username, password);

        if (aPerson != null) {
            this.setPerson(aPerson);
            return true;
        }

        return false;
    }

    @Override
    public Roles getRoles() {

        // not signed in
        if (!this.isSignedIn()) {
            return null;
        }

        Roles roles = new Roles();
        // roles.addAll(this.getAuthenticationService().getRolesOf(this.getPerson()));
        roles.add("USER");
        return roles;
    }

    public void setAuthenticationService(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }

}
