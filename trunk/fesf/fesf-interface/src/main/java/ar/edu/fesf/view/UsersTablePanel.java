package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxNamedAction;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

/**
 * TODO: description
 */
public class UsersTablePanel extends AjaxDataTablePanel<Person> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private PersonService personService;

    private IAjaxCallback<Person> callBack;

    public UsersTablePanel(final String id, final List<Person> list, final IAjaxCallback<Person> callBack) {
        super(id, list);
        this.setCallBack(callBack);
    }

    @Override
    public Panel actionsPanel(final String componentId, final IModel<Person> rowModel) {
        List<AjaxNamedAction<Person>> actions = new ArrayList<AjaxNamedAction<Person>>();
        actions.add(new AjaxNamedAction<Person>("more", this.getCallBack()));
        return new ActionsPanel<Person>(componentId, rowModel.getObject(), actions);
    }

    @Override
    public List<String> getColumnNames() {
        return this.getPersonService().getFieldNames();
    }

    @Override
    public List<String> getSortFields() {
        return this.getPersonService().getFieldsToSort();
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setCallBack(final IAjaxCallback<Person> callBack) {
        this.callBack = callBack;
    }

    public IAjaxCallback<Person> getCallBack() {
        return this.callBack;
    }

}
