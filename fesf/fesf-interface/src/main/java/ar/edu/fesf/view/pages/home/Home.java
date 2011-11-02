package ar.edu.fesf.view.pages.home;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.security.SecurityContextHelper;
import ar.edu.fesf.security.UserDetailsImpl;
import ar.edu.fesf.services.PersonService;
import ar.edu.fesf.wicket.application.SecuritySession;

public class Home extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PersonService personService;

    @SpringBean(name = "service.SecurityContextHelper")
    private SecurityContextHelper securityContextHelper;

    public Home() {
        super();
        this.initialize();
    }

    private void initialize() {
        UserDetailsImpl authenticatedUser = this.securityContextHelper.getAuthenticatedUser();

        if (this.securityContextHelper.isAuthenticatedUser()) {
            ((SecuritySession) this.getSession()).attachPerson();
            this.add(new Label("firstName", authenticatedUser.getFirstName()));
            this.add(new Label("lastName", authenticatedUser.getLastName()));
        } else {
            this.add(new Label("firstName", "").setVisible(false));
            this.add(new Label("lastName", ""));
        }

        final HomeContentPanel homeContentPanel = new HomeContentPanel("contentPanel");
        this.add(homeContentPanel);
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public SecurityContextHelper getSecurityContextHelper() {
        return this.securityContextHelper;
    }

    public void setSecurityContextHelper(final SecurityContextHelper securityContextHelper) {
        this.securityContextHelper = securityContextHelper;
    }

}
