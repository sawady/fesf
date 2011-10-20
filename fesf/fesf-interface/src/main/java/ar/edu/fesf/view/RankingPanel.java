package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class RankingPanel extends Panel {

    private static final long serialVersionUID = -7953872321445001861L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public RankingPanel(final String id, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.initialize(ajaxCallback);
    }

    private void initialize(final IAjaxCallback<Book> ajaxCallback) {
        this.add(new HorizontalBookPanel("top20", this.getBookService().getTop20(), ajaxCallback));
        this.add(new HorizontalBookPanel("recentlyAvailable", this.getBookService().getRecentlyAvailable(),
                ajaxCallback));
    }
}
