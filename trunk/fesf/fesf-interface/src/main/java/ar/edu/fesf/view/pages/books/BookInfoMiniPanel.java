package ar.edu.fesf.view.pages.books;

import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;

public class BookInfoMiniPanel extends Panel {

    private static final long serialVersionUID = -640542220956725256L;

    private Book book;

    public BookInfoMiniPanel(final String id, final Book book, final IAjaxCallback<Book> ajaxCallback) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.book = book;
        this.initialize(ajaxCallback);
    }

    private void initialize(final IAjaxCallback<Book> ajaxCallback) {
        // modelo para que el hdp no chille
        this.add(new AjaxFallbackLink<String>("link", new Model<String>(null)) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                ajaxCallback.apply(target, BookInfoMiniPanel.this.book);
            }

        });
        this.add(new Label("title"));
        this.add(new Label("authorNames", new Model<String>(this.concatenate(this.book.getAuthors()))));
        this.add(new Label("categoryNames", new Model<String>(this.concatenate(this.book.getCategories()))));
    }

    /* Gracias Java por hacer nuestra vida cada dia más feliz! */
    private String concatenate(final Set<Category> categories) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Category category : categories) {
            stringbuf.append(separator).append(category.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }

    private String concatenate(final List<Author> authors) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Author author : authors) {
            stringbuf.append(separator).append(author.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }
}
