package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class HomeUser extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private transient PersonService personService;

    public HomeUser() {
        super();
        this.initialize();
    }

    public final void initialize() {

        this.add(new AjaxDataTablePanel<Person>("table", this.personService.findAll(), this.personService.getFieldForSort(),
                this.personService.getFieldNames()));

        this.add(new Link<Object>("new") {

            private static final long serialVersionUID = 9066203239628200337L;

            @Override
            public void onClick() {
                this.setResponsePage(new EditPerson(new Person()));
            }
        });

    }

}
