package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.model.Book;

public class HorizontalBookPanel extends Panel {

    private List<Book> books;

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    private static final long serialVersionUID = 5948221763418992422L;

    public HorizontalBookPanel(final String id, final List<Book> books) {
        super(id);
        this.books = books;
        this.initialize();
    }

    private void initialize() {
        this.add(new ListView<Book>("bookList", this.getBooks()) {

            private static final long serialVersionUID = 5025190739259560931L;

            @Override
            protected void populateItem(final ListItem<Book> item) {
                item.add(new BookInfoMiniPanel("bookMiniPanel", item.getModelObject()));
            }
        });
    }
}
