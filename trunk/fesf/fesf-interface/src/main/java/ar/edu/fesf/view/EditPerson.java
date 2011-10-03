package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.PersonForm;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class EditPerson extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private PersonService personService;

    private Person person;

    public EditPerson(final Person person) {
        super();
        this.person = person;
        this.initialize();
    }

    private void initialize() {
        this.add(new PersonForm("form", this.person));
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

}
