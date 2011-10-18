package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.services.BookService;

public class HomeContentPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private RankingPanel rankingPanel;

    private BookSearchResultPanel bookSearchResultPanel;

    @SpringBean
    private BookService bookService;

    public HomeContentPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.setRankingPanel(new RankingPanel("content"));
        this.getRankingPanel().setOutputMarkupId(true);
        this.add(this.getRankingPanel());

        this.setBookSearchResultPanel(new BookSearchResultPanel("content", new ArrayList<Book>(), this
                .changeToMoreInfoPanel()));
        this.getBookSearchResultPanel().setOutputMarkupId(true);

        this.add(new HomeUserbarPanel("userbar", this.changeToRakingPanel()));
        this.add(new CategoriesSidebar("sidebar", this.changeToResultsPanel()));
        this.add(new BookSearchPanel("searchbar", this.changeToResultsPanel()));
    }

    public IAjaxCallback<?> changeToRakingPanel() {
        return new AjaxReplacePanel<Object>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Object book) {
                return HomeContentPanel.this.getRankingPanel();
            }

        };
    }

    public IAjaxCallback<Book> changeToMoreInfoPanel() {
        return new AjaxReplacePanel<Book>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Book book) {
                BookInfoPanel bookInfo = new BookInfoPanel("content", book,
                        HomeContentPanel.this.changeToLoaningFormPanel());
                bookInfo.setOutputMarkupId(true);
                return bookInfo;
            }

        };
    }

    public IAjaxCallback<Book> changeToLoaningFormPanel() {
        return new IAjaxCallback<Book>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Book book) {

                if (HomeContentPanel.this.getBookService().hasAvailableCopy(book)) {
                    LoaningFormPanel loaningFormPanel = new LoaningFormPanel("content", book,
                            HomeContentPanel.this.backToMoreInfoPanel());
                    loaningFormPanel.setOutputMarkupId(true);
                    HomeContentPanel.this.replace(loaningFormPanel);
                    target.add(loaningFormPanel);
                } else {
                    target.appendJavaScript("alert('You cannot motherfucker')");
                }
            }
        };

    }

    public IAjaxCallback<Loan> backToMoreInfoPanel() {
        return new AjaxReplacePanel<Loan>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Loan loan) {
                BookInfoPanel bookInfo = new BookInfoPanel("content", loan.getBook(),
                        HomeContentPanel.this.changeToLoaningFormPanel());
                bookInfo.setOutputMarkupId(true);
                return bookInfo;
            }

        };
    }

    public IAjaxCallback<List<Book>> changeToResultsPanel() {
        return new AjaxReplacePanel<List<Book>>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final List<Book> list) {
                HomeContentPanel.this.getBookSearchResultPanel().replaceTable(target, list);
                return HomeContentPanel.this.getBookSearchResultPanel();
            }

        };
    }

    /* Accessors */
    public void setRankingPanel(final RankingPanel rankingPanel) {
        this.rankingPanel = rankingPanel;
    }

    public RankingPanel getRankingPanel() {
        return this.rankingPanel;
    }

    public BookSearchResultPanel getBookSearchResultPanel() {
        return this.bookSearchResultPanel;
    }

    public void setBookSearchResultPanel(final BookSearchResultPanel bookSearchResultPanel) {
        this.bookSearchResultPanel = bookSearchResultPanel;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
