package ar.edu.fesf.view.pages.persons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MinimumValidator;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;
import ar.edu.fesf.services.dtos.PersonDTO;

public class PersonFormFieldsPanel extends PanelServiceToForm<PersonDTO> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PersonService personService;

    private PersonDTO personDTO;

    private IAjaxCallback<Person> ajaxCallback;

    public PersonFormFieldsPanel(final String id, final PersonDTO personDTO, final IAjaxCallback<Person> ajaxCallback) {
        super(id);
        this.personDTO = personDTO;
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        // TODO validar datos algun dia
        this.add(new RequiredTextField<String>("name"));
        this.add(new RequiredTextField<String>("surname"));
        this.add(new RequiredTextField<Integer>("age", Integer.class).add(new MinimumValidator<Integer>(0)));
        this.add(new RequiredTextField<String>("address"));
        this.add(new RequiredTextField<String>("phone"));
        this.add(new RequiredTextField<String>("email"));
        this.add(new RequiredTextField<String>("userid"));
        this.add(new PasswordTextField("password").setRequired(true));

    }

    @Override
    public PersonDTO getObject() {
        return this.getPersonDTO();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<PersonDTO> form) {
        Person personDB = this.getPersonService().registerPerson(this.personDTO);
        this.getAjaxCallback().apply(target, personDB);
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public PersonDTO getPersonDTO() {
        return this.personDTO;
    }

    public void setPersonDTO(final PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public IAjaxCallback<Person> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setAjaxCallback(final IAjaxCallback<Person> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

}
