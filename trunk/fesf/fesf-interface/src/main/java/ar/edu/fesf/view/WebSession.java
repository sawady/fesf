package ar.edu.fesf.view;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.services.AuthenticationService;

public class WebSession extends AuthenticatedWebSession {

    @SpringBean(name = "service.authentication")
    private AuthenticationService authenticationService;

    public WebSession(final Request request) {
        super(request);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean authenticate(final String username, final String password) {
        return this.getAuthenticationService().authenticate(username, password);
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        for (ar.edu.fesf.model.Role role : this.getAuthenticationService().getRoles()) {
            roles.add(role.toString());
        }
        return roles;
    }

    public void setAuthenticationService(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }

}
