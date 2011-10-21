package ar.edu.fesf.view;

import java.util.Collection;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.ISBN;
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

    // TODO representar mejor el libro
    private void initialize(final IAjaxCallback<Book> callback, final Book book) {
        this.add(new Label("title"));
        this.add(new Label("authorNames", new Model<String>(this.concatenate(this.getBookService()
                .<Nameable> getCollectionField(book, "authors")))));
        this.add(new Label("publisher", new Model<String>(this.getBookService().getPublisherName(book))));
        this.add(new Label("categoryNames", new Model<String>(this.concatenate(this.getBookService()
                .<Nameable> getCollectionField(book, "categories")))));
        this.add(new Label("description"));
        this.add(new Label("isbn", new Model<String>(this.getBookService().<ISBN> getField(book, "isbn").getValue())));
        // this.add(new Label("countOfRegistedCopies",
        this.add(new Label("countOfAvailableCopies", this.getBookService().getCountOfAvailableCopies(book).toString()));

        this.add(new AjaxFallbackLink<String>("borrowIt", new Model<String>(null)) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                callback.callback(target, book);
            }

        });
    }

    // /* Gracias Java por hacer nuestra vida cada dia más feliz! */
    // private String concatenate(final Set<Category> categories) {
    // String separator = "";
    // StringBuffer stringbuf = new StringBuffer();
    // for (Category category : categories) {
    // stringbuf.append(separator).append(category.getName());
    // if ("".equals(separator)) {
    // separator = ", ";
    // }
    // }
    // return stringbuf.toString();
    // }

    private String concatenate(final Collection<Nameable> nameables) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Nameable nameable : nameables) {
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
