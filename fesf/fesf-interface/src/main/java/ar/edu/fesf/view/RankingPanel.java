package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.RankingService;

public class RankingPanel extends Panel {

    private static final long serialVersionUID = -7953872321445001861L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    @SpringBean(name = "service.ranking")
    private RankingService rankingService;

    private IAjaxCallback<Book> ajaxCallback;

    public RankingPanel(final String id, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        this.add(new HorizontalBookPanel("top20", this.getBookService().getTop20(), this.ajaxCallback));
        this.add(new HorizontalBookPanel("recentlyAvailable", this.getRankingService().getRecentlyAvailable(),
                this.ajaxCallback));
    }

    public void updateTop20(final AjaxRequestTarget target) {
        this.replace(new HorizontalBookPanel("top20", this.getBookService().getTop20(), this.ajaxCallback));
    }

    /* Accessors */
    public void setRankingService(final RankingService rankingService) {
        this.rankingService = rankingService;
    }

    public RankingService getRankingService() {
        return this.rankingService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public IAjaxCallback<Book> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setAjaxCallback(final IAjaxCallback<Book> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

}
