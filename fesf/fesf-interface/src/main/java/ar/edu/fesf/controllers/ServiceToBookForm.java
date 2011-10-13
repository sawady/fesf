package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.BookFormFieldsPanel;

public class ServiceToBookForm implements ServiceToForm<Book> {

    private static final long serialVersionUID = -2681835593630799248L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private Book book;

    private IAjaxCallback<Form<Book>> iAjaxCallback;

    /* Methods */

    public ServiceToBookForm(final Book book, final IAjaxCallback<Form<Book>> iAjaxCallback,
            final BookService bookService) {
        this.book = book;
        this.iAjaxCallback = iAjaxCallback;
        this.bookService = bookService;
    }

    @Override
    public Book getObject() {
        return this.getBook();
    }

    @Override
    public Panel getFieldsPanel(final String id) {
        BookFormFieldsPanel bookFormFieldsPanel = new BookFormFieldsPanel(id);
        bookFormFieldsPanel.setOutputMarkupId(true);
        return bookFormFieldsPanel;
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<Book> form) {
        this.getBookService().save(this.getBook());
        this.getiAjaxCallback().callback(target, form);
    }

    /* Accessors */

    public void setiAjaxCallback(final IAjaxCallback<Form<Book>> iAjaxCallback) {
        this.iAjaxCallback = iAjaxCallback;
    }

    public IAjaxCallback<Form<Book>> getiAjaxCallback() {
        return this.iAjaxCallback;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }
}