package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

@AuthorizeAction(action = "ENABLE", roles = { "LIBRARIAN" })
public class LibrarianTabbedPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LibrarianTabbedPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {

        List<ITab> tabs = new ArrayList<ITab>();

        tabs.add(new AbstractTab(new Model<String>("Main Panel")) {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(final String panelId) {
                return new LibrarianHomeMainPanel(panelId);
            }
        });

        tabs.add(new AbstractTab(new Model<String>("Users")) {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(final String panelId) {
                return new UsersPanel(panelId);
            }
        });

        tabs.add(new AbstractTab(new Model<String>("Books")) {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(final String panelId) {
                return new LibrarianBooksPanel(panelId);
            }
        });

        tabs.add(new AbstractTab(new Model<String>("Loanees")) {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(final String panelId) {
                return new LoaneesPanel(panelId);
            }
        });

        this.add(new AjaxTabbedPanel("tabbedPanel", tabs));

    }
}
