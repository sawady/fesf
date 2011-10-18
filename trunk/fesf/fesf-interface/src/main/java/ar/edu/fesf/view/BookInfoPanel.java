package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class BookInfoPanel extends Panel {

    @SpringBean
    private BookService bookService;

    private static final long serialVersionUID = -640542220956725256L;

    public BookInfoPanel(final String id, final Book book, final IAjaxCallback<Book> callback) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize(callback, book);
    }

    // TODO representar mejor el libro
    private void initialize(final IAjaxCallback<Book> callback, final Book book) {
        this.add(new Label("title"));
        this.add(new Label("countOfCopies", this.getBookService().getCountOfCopies(book).toString()));
        this.add(new AjaxFallbackLink<String>("borrowIt", new Model<String>(null)) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                callback.callback(target, book);
            }

        });
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }
}
