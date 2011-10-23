package ar.edu.fesf.view;

import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Nameable;
import ar.edu.fesf.services.BookService;

public class BookInfoPanel extends Panel {

    @SpringBean(name = "service.book")
    private BookService bookService;

    private static final long serialVersionUID = -640542220956725256L;

    public BookInfoPanel(final String id, final Book book, final IAjaxCallback<Book> callback) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize(callback, book);
    }

    // TODO faltan un par de campos
    private void initialize(final IAjaxCallback<Book> callback, final Book book) {

        this.add(new Label("title"));
        this.add(new Label("authorNames", this.concatenate(book.getAuthors())));
        this.add(new Label("publisher.name"));
        this.add(new Label("categoryNames", this.concatenate(book.getCategories())));
        this.add(new Label("description"));
        this.add(new Label("isbn.value"));
        this.add(new Label("countOfAvailableCopies", book.getCountOfAvailableCopies().toString()));

        this.add(new AjaxFallbackLink<String>("borrowIt", new Model<String>(null)) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                callback.callback(target, book);
            }

        });
    }

    // Gracias java por no permitirme abstraer esto
    private String concatenate(final Set<Category> list) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Nameable nameable : list) {
            stringbuf.append(separator).append(nameable.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }

    private String concatenate(final List<Author> list) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Nameable nameable : list) {
            stringbuf.append(separator).append(nameable.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }
}
