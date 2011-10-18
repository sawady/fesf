package ar.edu.fesf.view;

import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;

public class BookInfoMiniPanel extends Panel {

    private static final long serialVersionUID = -640542220956725256L;

    private Book book;

    public BookInfoMiniPanel(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.book = book;
        this.initialize();
    }

    private void initialize() {
        this.add(new Label("title"));
        this.add(new Label("authorNames", new Model<String>(this.concatenate(this.book.getAuthors()))));
        this.add(new Label("category", new Model<String>(this.concatenate(this.book.getCategories()))));
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
