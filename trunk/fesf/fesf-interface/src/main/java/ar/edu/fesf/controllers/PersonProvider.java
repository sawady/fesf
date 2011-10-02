package ar.edu.fesf.controllers;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class PersonProvider implements IDataProvider<Person> {

    private static final long serialVersionUID = -2420924365103187295L;

    private PersonService personService;

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonProvider(final PersonService personService) {
        super();
        this.personService = personService;
    }

    @Override
    public void detach() {
        // TODO preguntar
    }

    @Override
    public Iterator<Person> iterator(final int first, final int count) {
        return this.getPersonService().getAllPerson().iterator();
    }

    @Override
    public int size() {
        return this.getPersonService().getAllPerson().size();
    }

    @Override
    public IModel<Person> model(final Person object) {
        return new Model<Person>(object);
    }

}
