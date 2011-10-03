package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.GenericProvider;
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

        @SuppressWarnings("unchecked")
        IColumn<Book>[] columns = new PropertyColumn[1];

        columns[0] = new PropertyColumn<Book>(new Model<String>("Title"), "title");

        DataTable<Book> dataTable = new DataTable<Book>("table", columns, new GenericProvider<Book>(
                this.getBookService()), 10);
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable));
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, null));
        this.add(dataTable);

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
