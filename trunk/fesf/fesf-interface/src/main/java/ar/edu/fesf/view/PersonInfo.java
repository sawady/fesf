package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;

public class PersonInfo extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "personExample")
    private transient Person person;

    public PersonInfo() {
        super();
        this.initialize();
    }

    private void initialize() {
        throw new UnsupportedOperationException();
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }

}
