package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class Home extends WebPage {

    Panel contentPanel;

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

    public void changeContentPanel(final AjaxRequestTarget target, final List<Book> books) {
        BookSearchResultPanel resultPanel = new BookSearchResultPanel("contentPanel", books);
        resultPanel.setOutputMarkupId(true);
        this.contentPanel.replaceWith(resultPanel);
        this.contentPanel = resultPanel;
        target.addComponent(this);
    }

    private void initialize() {
        this.setOutputMarkupId(true);
        this.contentPanel = new RankingPanel("contentPanel");
        this.contentPanel.setOutputMarkupId(true);
        this.add(this.contentPanel);
        this.add(new Sidebar("sidebar"));
        this.add(new BookSearchPanel("searchbar", this));
        // TODO terminar el buscador
        // this.add(new Link<Object>("search") {
        // private static final long serialVersionUID = -4296821998512737231L;
        //
        // @Override
        // public void onClick() {
        // this.setResponsePage(new SearchBook(Home.this.getBookService().findAll()));
        // }
        // });
    }
}
