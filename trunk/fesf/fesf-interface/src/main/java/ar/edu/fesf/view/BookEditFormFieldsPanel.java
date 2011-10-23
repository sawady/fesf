package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.dtos.EditBookDTO;

public class BookEditFormFieldsPanel extends PanelServiceToForm<EditBookDTO> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BookService bookService;

    private EditBookDTO book;

    private IAjaxCallback<Book> ajaxCallback;

    public BookEditFormFieldsPanel(final String id, final EditBookDTO book, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.book = book;
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        this.add(new RequiredTextField<String>("title"));
        this.add(new RequiredTextField<String>("isbn"));
        this.add(new RequiredTextField<String>("publisher"));
        this.add(new TextArea<String>("description"));
    }

    @Override
    public EditBookDTO getObject() {
        return this.getBook();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<EditBookDTO> form) {
        this.getBookService().registerEditBookDTO(this.book);
        this.getAjaxCallback().callback(target, null);
    }

    public void setBook(final EditBookDTO book) {
        this.book = book;
    }

    public EditBookDTO getBook() {
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
