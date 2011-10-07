package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.BookSearchForm;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;

public class BookSearchPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private IAjaxCallback<List<Book>> callback;

    public BookSearchPanel(final String id, final IAjaxCallback<List<Book>> callback) {
        super(id);
        this.setCallback(callback);
        this.initialize();
    }

    private void initialize() {
        this.add(new BookSearchForm("form", this));
    }

    public void recieveResult(final AjaxRequestTarget target, final List<Book> books) {
        this.getCallback().callback(target, books);
    }

    private void setCallback(final IAjaxCallback<List<Book>> callback) {
        this.callback = callback;
    }

    private IAjaxCallback<List<Book>> getCallback() {
        return this.callback;
    }

}
