package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class UsersPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private transient PersonService personService;

    public UsersPanel(final String id) {
        super(id);
        this.initialize();
    }

    public final void initialize() {

        this.add(new AjaxDataTablePanel<Person>("table", this.personService.findAll(), this.personService
                .getFieldForSort(), this.personService.getFieldNames()));

    }

}
