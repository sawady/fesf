package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;

public class HorizontalBookPanel extends Panel {

    private List<Book> books;

    private static final long serialVersionUID = 5948221763418992422L;

    /* Methods */

    public HorizontalBookPanel(final String id, final List<Book> books, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.books = books;
        this.initialize(ajaxCallback);
    }

    private void initialize(final IAjaxCallback<Book> ajaxCallback) {
        this.add(new ListView<Book>("bookList", this.getBooks()) {

            private static final long serialVersionUID = 5025190739259560931L;

            @Override
            protected void populateItem(final ListItem<Book> item) {
                item.add(new BookInfoMiniPanel("bookMiniPanel", item.getModelObject(), ajaxCallback));
            }
        });
    }

    /* Accessors */

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }
}
