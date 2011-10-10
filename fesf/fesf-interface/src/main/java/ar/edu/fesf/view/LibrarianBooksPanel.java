package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;

public class LibrarianBooksPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LibrarianBooksPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        LibrarianBooksContentPanel librarianBooksContentPanel = new LibrarianBooksContentPanel("contentPanel");
        this.add(new BookSearchPanel("search", librarianBooksContentPanel.changeBookTablePanel()));
        this.add(librarianBooksContentPanel);
    }
}
