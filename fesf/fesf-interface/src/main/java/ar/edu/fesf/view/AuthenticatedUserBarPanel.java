package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.services.PersonService;

public class AuthenticatedUserBarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PersonService personService;

    public AuthenticatedUserBarPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(new AjaxFallbackLink<String>("signOut") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                // TODO
            }

        });
        AjaxFallbackLink<String> profileLink = new AjaxFallbackLink<String>("profile") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                // TODO
            }

        };
        String personName = this.getPersonService().findAll().get(0).getName();
        profileLink.add(new Label("welcome", "Welcome " + personName));
        this.add(profileLink);
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }
}
