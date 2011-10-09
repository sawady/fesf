package ar.edu.fesf.controllers;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class PersonForm extends Form<Person> {

    private static final long serialVersionUID = -3699540231028644958L;

    @SpringBean(name = "service.person")
    private transient PersonService personService;

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonForm(final String id, final Person person) {
        super(id, new CompoundPropertyModel<Person>(person));
        this.initialize();
    }

    // TODO completar con todos los campos
    private void initialize() {
        this.add(new TextField<String>("name"));
        this.add(new Button("save"));
    }

    @Override
    public void onSubmit() {
        this.getPersonService().save(this.getModelObject());
        // this.setResponsePage(UsersPanel.class);
    }
}
