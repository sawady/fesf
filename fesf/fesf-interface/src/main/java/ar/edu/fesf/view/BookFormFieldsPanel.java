package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class BookFormFieldsPanel extends PanelServiceToForm<Book> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BookService bookService;

    private Book book;

    private IAjaxCallback<Book> ajaxCallback;

    public BookFormFieldsPanel(final String id, final Book book, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.book = book;
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        this.add(new RequiredTextField<String>("title"));
    }

    @Override
    public Book getObject() {
        return this.getBook();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<Book> form) {
        this.getBookService().save(this.book);
        this.getAjaxCallback().callback(target, this.getBook());
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public void setAjaxCallback(final IAjaxCallback<Book> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

    public IAjaxCallback<Book> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
