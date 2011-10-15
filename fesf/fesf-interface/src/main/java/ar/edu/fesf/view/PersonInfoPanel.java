package ar.edu.fesf.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class PersonInfoPanel extends Panel {

    private static final long serialVersionUID = -640542220956725256L;

    @SpringBean(name = "service.person")
    private PersonService personService;

    /* Methods */

    public PersonInfoPanel(final String id, final Person person) {
        super(id, new CompoundPropertyModel<Person>(person));
        this.initialize();
    }

    private void initialize() {
        this.add(new Label("name"));
    }

    /* Accessors */

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }
}
