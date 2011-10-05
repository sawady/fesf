package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.SearchBookForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class SearchBook extends WebPage {

    @SpringBean(name = "service.book")
    private BookService bookService;

    private Book actualSearch;

    private List<Book> books;

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public SearchBook(final List<Book> books) {
        this(books, new Book());
    }

    public SearchBook(final List<Book> books, final Book actualSearch) {
        super();
        this.setActualSearch(actualSearch);
        this.books = books;
        this.initialize();
    }

    private void initialize() {

        this.add(new AjaxDataTable<Book>("table", this.getBooks(), this.getBookService().getFieldForSort(), this
                .getBookService().getFieldNames()));

        this.add(new Link<Object>("new") {

            private static final long serialVersionUID = 9066203239628200337L;

            @Override
            public void onClick() {
                this.setResponsePage(new EditBook(new Book()));
            }
        });

        this.add(new SearchBookForm("form", this.getActualSearch()));

    }

    private void setActualSearch(final Book actualSearch) {
        this.actualSearch = actualSearch;
    }

    private Book getActualSearch() {
        return this.actualSearch;
    }

}
