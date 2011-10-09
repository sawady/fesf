package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class BooksPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private BookSearchResultPanel bookSearchResultPanel;

    public BooksPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(new BookSearchPanel("search", this.bookResultsCallBack()));
        this.setBookSearchResultPanel(new BookSearchResultPanel("resultPanel", this.getBookService().findAll()));
        this.getBookSearchResultPanel().setOutputMarkupId(true);
        this.add(this.getBookSearchResultPanel());
    }

    private IAjaxCallback<List<Book>> bookResultsCallBack() {
        return new IAjaxCallback<List<Book>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final List<Book> books) {
                // TODO ver si se puede mejorar
                BooksPanel.this.getBookSearchResultPanel().replaceTable(target, books);
                target.addComponent(BooksPanel.this.getBookSearchResultPanel());
            }

        };
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    private void setBookSearchResultPanel(final BookSearchResultPanel bookSearchResultPanel) {
        this.bookSearchResultPanel = bookSearchResultPanel;
    }

    private BookSearchResultPanel getBookSearchResultPanel() {
        return this.bookSearchResultPanel;
    }
}
