package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.BookSearchForm;
import ar.edu.fesf.model.Book;

public class BookSearchPanel extends Panel {

    private static final long serialVersionUID = 5361395722175462134L;

    private Book actualSearch;

    public Book getActualSearch() {
        return this.actualSearch;
    }

    public void setActualSearch(final Book actualSearch) {
        this.actualSearch = actualSearch;
    }

    public BookSearchPanel(final String id) {
        this(id, new Book());
    }

    public BookSearchPanel(final String id, final Book book) {
        super(id);
        this.actualSearch = book;
        this.initialize();
    }

    private void initialize() {
        BookSearchForm bookSearchForm = new BookSearchForm("form", this.getActualSearch());
        this.add(bookSearchForm);

    }

}
