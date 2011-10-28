package ar.edu.fesf.view;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.AuthenticationService;
import ar.edu.fesf.services.PersonService;

public class MyWebSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;

    // @SpringBean(name = "authenticationManager")
    // private AuthenticationManager authenticationManager;

    @SpringBean(name = "service.person")
    private PersonService personService;

    @SpringBean(name = "service.authentication")
    private AuthenticationService authenticationService;

    private Person person;

    public MyWebSession(final Request request) {
        super(request);
        Injector.get().inject(this);
        this.ensureDependenciesNotNull();
        // this.getApplication().getSecuritySettings()
        // .setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(new IRoleCheckingStrategy() {
        //
        // @Override
        // public boolean hasAnyRole(final Roles roles) {
        // // if (MyWebSession.this.getPerson() == null) {
        // // return false;
        // // } else {
        // // return roles.contains(MyWebSession.this.getPerson().getUserInfo().getRole());
        // // }
        // return true;
        // }
        //
        // }));
    }

    private void ensureDependenciesNotNull() {
        if (this.authenticationService == null) {
            throw new IllegalStateException("Session requires an authenticationManager.");
        }
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        boolean canSignIn = this.getAuthenticationService().authenticate(username, password);

        if (canSignIn) {
            this.setPerson(this.getAuthenticationService().findPersonWithUserInfo(username));
        }

        return canSignIn;
    }

    // @Override
    // public boolean authenticate(final String username, final String password) {
    // boolean authenticated = false;
    // try {
    // Authentication authentication = this.authenticationManager
    // .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // authenticated = authentication.isAuthenticated();
    // } catch (AuthenticationException e) {
    // authenticated = false;
    // }
    // return authenticated;
    // }

    // @Override
    // public Roles getRoles() {
    // Roles roles = new Roles();
    //
    // // not signed in
    // if (this.isSignedIn()) {
    // roles.addAll(this.getAuthenticationService().getRolesOf(this.getPerson()));
    // }
    //
    // return roles;
    // }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        this.getRolesIfSignedIn(roles);
        return roles;
    }

    private void getRolesIfSignedIn(final Roles roles) {
        if (this.isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            this.addRolesFromAuthentication(roles, authentication);
        }
    }

    private void addRolesFromAuthentication(final Roles roles, final Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }

    public void signOutPerson() {
        this.setPerson(null);
    }

    /* Accessors */
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

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    // public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
    // this.authenticationManager = authenticationManager;
    // }
    //
    // public AuthenticationManager getAuthenticationManager() {
    // return this.authenticationManager;
    // }
}
