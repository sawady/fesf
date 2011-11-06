package ar.edu.fesf.view.pages.librarian;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.view.pages.generic.GenericFormPanel;
import ar.edu.fesf.view.pages.signin.SignInFieldsPanel;

public class LibrarianHomeContentPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private LibrarianTabbedPanel librarianTabbedPanel;

    /* Methods */

    public LibrarianHomeContentPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {

        this.setLibrarianTabbedPanel(new LibrarianTabbedPanel("content"));

        if (this.getLibrarianTabbedPanel().isEnableAllowed()) {
            this.add(this.getLibrarianTabbedPanel());
        } else {
            this.add(new GenericFormPanel<UserInfo>("content") {

                private static final long serialVersionUID = 1L;

                @Override
                public PanelServiceToForm<UserInfo> getFieldsPanel(final String id) {
                    return new SignInFieldsPanel(id, new UserInfo(), LibrarianHomeContentPanel.this
                            .accessLibrarianTabbedPanel());
                }

            }.setOutputMarkupId(true));
        }

    }

    public IAjaxCallback<Person> accessLibrarianTabbedPanel() {
        return new AjaxReplacePanel<Person>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final Person person) {
                // TODO hacer algo con person
                return LibrarianHomeContentPanel.this.getLibrarianTabbedPanel();
            }
        };

    }

    /* Accessors */

    public void setLibrarianTabbedPanel(final LibrarianTabbedPanel librarianTabbedPanel) {
        this.librarianTabbedPanel = librarianTabbedPanel;
    }

    public LibrarianTabbedPanel getLibrarianTabbedPanel() {
        return this.librarianTabbedPanel;
    }
}
