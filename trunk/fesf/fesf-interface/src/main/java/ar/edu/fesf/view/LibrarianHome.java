package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class LibrarianHome extends WebPage {

    public LibrarianHome() {
        super();
        this.initalize();
    }

    private void initalize() {

        List<ITab> tabs = new ArrayList<ITab>();

        tabs.add(new AbstractTab(new Model<String>("Main Panel")) {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(final String panelId) {
                return new LibrarianMainPanel(panelId);
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
                return new BooksPanel(panelId);
            }
        });

        AjaxTabbedPanel ajaxTabbedPanel = new AjaxTabbedPanel("tabbedPanel", tabs);

        this.add(ajaxTabbedPanel);
    }

}
