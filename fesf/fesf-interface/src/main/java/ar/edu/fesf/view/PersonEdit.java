package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class PersonEdit extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private transient PersonService personService;

    private Person person;

    public PersonEdit(final Person person) {
        super();
        this.person = person;
        this.initialize();
    }

    private void initialize() {
        // TextField<String> name = new TextField<String>("name", new Model<String>(this.person.getName()));

        // Form<Person> form = new Form<Person>("form", new Model<Person>(this.person)) {
        // private static final long serialVersionUID = -1069357591260846362L;
        //
        // @Override
        // protected void onSubmit() {
        // PersonEdit.this.getPersonService().addPerson(PersonEdit.this.person);
        // }
        // };
        //
        // form.add(name);
        // form.add(new Button("submit"));
        // this.add(form);
        this.add(new PersonForm("personForm", this.person));
    }

    class PersonForm extends Form<Person> {
        private static final long serialVersionUID = -3699540231028644958L;

        public PersonForm(final String id, final Person person) {
            super(id, new CompoundPropertyModel<Person>(person));
            this.add(new TextField<String>("name"));
            this.add(new Button("save"));
        }

        @Override
        public void onSubmit() {
            PersonEdit.this.getPersonService().addPerson(PersonEdit.this.person);
            this.setResponsePage(Home.class);
        }
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
