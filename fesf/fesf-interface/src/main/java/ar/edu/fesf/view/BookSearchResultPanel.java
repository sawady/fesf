package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class BookSearchResultPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    private List<Book> books;

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    public BookSearchResultPanel(final String id, final List<Book> books) {
        this(id, books, new Book());
    }

    public BookSearchResultPanel(final String id, final List<Book> books, final Book actualSearch) {
        super(id);
        this.books = books;
        this.initialize();
    }

    private void initialize() {
        AjaxDataTablePanel<Book> dataPanel = new AjaxDataTablePanel<Book>("table", this.getBooks(), this
                .getBookService().getFieldForSort(), this.getBookService().getFieldNames());
        dataPanel.setOutputMarkupId(true);
        this.add(dataPanel);
    }

}
