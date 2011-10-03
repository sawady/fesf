package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.BookForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class EditBook extends WebPage {

    @SpringBean(name = "service.book")
    private BookService bookService;

    private Book book;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public EditBook(final Book book) {
        super();
        this.book = book;
        this.initialize();
    }

    private void initialize() {
        this.add(new BookForm("form", this.getBook()));
    }

}
