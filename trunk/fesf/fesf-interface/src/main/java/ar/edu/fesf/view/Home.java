package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.services.PersonService;

public class Home extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PersonService personService;

    public Home() {
        super();
        this.initialize();
    }

    private void initialize() {
        WebSession webSession = (WebSession) this.getSession();
        webSession.setPerson(this.getPersonService().findAll().get(0));
        webSession.getPerson();
        final HomeContentPanel homeContentPanel = new HomeContentPanel("contentPanel");
        this.add(homeContentPanel);
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

}
