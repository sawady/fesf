package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.BookSearchForm;
import ar.edu.fesf.model.Book;

public class BookSearchPanel extends Panel {

    private static final long serialVersionUID = 5361395722175462134L;

    private Book actualSearch;

    private Home page;

    public Book getActualSearch() {
        return this.actualSearch;
    }

    public void setActualSearch(final Book actualSearch) {
        this.actualSearch = actualSearch;
    }

    public BookSearchPanel(final String id, final Home page) {
        this(id, new Book(), page);
    }

    public BookSearchPanel(final String id, final Book book, final Home page) {
        super(id);
        this.actualSearch = book;
        this.page = page;
        this.initialize(page);
    }

    private void initialize(final WebPage page) {
        BookSearchForm bookSearchForm = new BookSearchForm("form", this.getActualSearch(), this);
        this.add(bookSearchForm);
    }

    public void recieveResult(final AjaxRequestTarget target, final List<Book> books) {
        this.page.changeContentPanel(target, books);
    }

}
