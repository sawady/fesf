package ar.edu.fesf.view.pages.librarian;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;
import ar.edu.fesf.view.pages.persons.PersonInfoPanel;

public class UsersPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private UsersTablePanel usersTablePanel;

    @SpringBean(name = "service.person")
    private PersonService personService;

    /* Methods */

    public UsersPanel(final String id) {
        super(id);
        this.initialize();
    }

    public final void initialize() {
        this.setUsersTablePanel(new UsersTablePanel("content", this.getPersonService().findAll(), this
                .changeToMoreInfoPanel()));
        this.getUsersTablePanel().setOutputMarkupId(true);
        this.add(this.getUsersTablePanel());
    }

    public IAjaxCallback<Person> changeToMoreInfoPanel() {
        return new AjaxReplacePanel<Person>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final Person person) {
                PersonInfoPanel personInfo = new PersonInfoPanel("content", UsersPanel.this.getPersonService()
                        .initializePersonInfo(person));
                personInfo.setOutputMarkupId(true);
                return personInfo;
            }

        };
    }

    public IAjaxCallback<List<Person>> changeToTablePanel() {
        return new AjaxReplacePanel<List<Person>>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final List<Person> persons) {
                UsersPanel.this.getUsersTablePanel().replaceTable(persons);
                return UsersPanel.this.getUsersTablePanel();
            }

        };
    }

    /* Accessors */

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    private void setUsersTablePanel(final UsersTablePanel usersTablePanel) {
        this.usersTablePanel = usersTablePanel;
    }

    private UsersTablePanel getUsersTablePanel() {
        return this.usersTablePanel;
    }

}
