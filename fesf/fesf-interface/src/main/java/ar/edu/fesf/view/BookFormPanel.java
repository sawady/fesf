package ar.edu.fesf.view;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.BookForm;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;

public class BookFormPanel extends Panel {

    private static final long serialVersionUID = -2681835593630799248L;

    public BookFormPanel(final String id, final Book book, final IAjaxCallback<Form<Book>> iAjaxCallback) {
        super(id);
        this.initialize(book, iAjaxCallback);
    }

    private void initialize(final Book book, final IAjaxCallback<Form<Book>> iAjaxCallback) {
        this.add(new BookForm("form", book, iAjaxCallback));
    }

}
