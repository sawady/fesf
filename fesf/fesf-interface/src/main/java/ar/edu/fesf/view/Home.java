package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class Home extends WebPage {

    private Panel contentPanel;

    private BookSearchResultPanel bookSearchResultPanel;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public Home() {
        super();
        this.initialize();
    }

    private IAjaxCallback<List<Book>> changeContentPanelCallback() {
        return new IAjaxCallback<List<Book>>() {
            @Override
            public void callback(final AjaxRequestTarget target, final List<Book> books) {
                if (!Home.this.getContentPanel().equals(Home.this.getBookSearchResultPanel())) {
                    Home.this.getContentPanel().replaceWith(Home.this.getBookSearchResultPanel());
                    Home.this.setContentPanel(Home.this.getBookSearchResultPanel());
                }
                target.addComponent(Home.this.getContentPanel());
                Home.this.getBookSearchResultPanel().replaceTable(target, books);
            }
        };
    }

    private void initialize() {
        BookSearchResultPanel resultSearchPanel = new BookSearchResultPanel("contentPanel");
        resultSearchPanel.setOutputMarkupId(true);
        this.setBookSearchResultPanel(resultSearchPanel);

        RankingPanel rankingPanel = new RankingPanel("contentPanel");
        rankingPanel.setOutputMarkupId(true);
        this.setContentPanel(rankingPanel);
        this.add(rankingPanel);

        this.add(new Sidebar("sidebar", this.changeContentPanelCallback()));
        this.add(new BookSearchPanel("searchbar", this.changeContentPanelCallback()));
    }

    private void setContentPanel(final Panel contentPanel) {
        this.contentPanel = contentPanel;
    }

    private Panel getContentPanel() {
        return this.contentPanel;
    }

    public void setBookSearchResultPanel(final BookSearchResultPanel bookSearchResultPanel) {
        this.bookSearchResultPanel = bookSearchResultPanel;
    }

    public BookSearchResultPanel getBookSearchResultPanel() {
        return this.bookSearchResultPanel;
    }
}
