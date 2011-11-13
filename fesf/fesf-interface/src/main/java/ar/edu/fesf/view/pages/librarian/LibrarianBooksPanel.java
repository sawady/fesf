package ar.edu.fesf.view.pages.librarian;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.scala.view.pages.ScalaBookSearchPanel;

public class LibrarianBooksPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LibrarianBooksPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        LibrarianBooksContentPanel librarianBooksContentPanel = new LibrarianBooksContentPanel("contentPanel");
        this.add(new ScalaBookSearchPanel("search", librarianBooksContentPanel.changeBookTablePanel()));
        this.add(librarianBooksContentPanel);
    }
}
