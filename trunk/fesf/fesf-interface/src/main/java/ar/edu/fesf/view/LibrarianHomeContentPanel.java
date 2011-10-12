package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Person;

public class LibrarianHomeContentPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private LibrarianTabbedPanel librarianTabbedPanel;

    public LibrarianHomeContentPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {

        this.setLibrarianTabbedPanel(new LibrarianTabbedPanel("content"));

        if (this.getLibrarianTabbedPanel().isEnableAllowed()) {
            this.add(this.getLibrarianTabbedPanel());
        } else {
            this.add(new MySignInPanel("content", this.accessLibrarianTabbedPanel()));
        }

    }

    public IAjaxCallback<Person> accessLibrarianTabbedPanel() {
        return new AjaxReplacePanel<Person>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Person person) {
                // TODO hacer algo con person
                return LibrarianHomeContentPanel.this.getLibrarianTabbedPanel();
            }
        };

    }

    public void setLibrarianTabbedPanel(final LibrarianTabbedPanel librarianTabbedPanel) {
        this.librarianTabbedPanel = librarianTabbedPanel;
    }

    public LibrarianTabbedPanel getLibrarianTabbedPanel() {
        return this.librarianTabbedPanel;
    }
}
