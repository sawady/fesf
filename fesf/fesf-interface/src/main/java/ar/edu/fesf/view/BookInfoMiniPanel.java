package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;

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
        this.add(new Label("authorNames", new Model<String>(this.concatenateAuthorNames(this.book.getAuthors()))));
    }

    private String concatenateAuthorNames(final List<Author> authors) {
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
