package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class PersonInfoPanel extends Panel {

    private static final long serialVersionUID = -640542220956725256L;

    @SpringBean(name = "service.person")
    private PersonService personService;

    public PersonInfoPanel(final String id, final Person person, final IAjaxCallback<List<Person>> callback) {
        super(id, new CompoundPropertyModel<Person>(person));
        this.initialize(callback);
    }

    private void initialize(final IAjaxCallback<List<Person>> callback) {
        this.add(new Label("name"));
        this.add(new AjaxFallbackLink<Person>("back") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                callback.callback(target, PersonInfoPanel.this.getPersonService().findAll());
            }

        });
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }
}
