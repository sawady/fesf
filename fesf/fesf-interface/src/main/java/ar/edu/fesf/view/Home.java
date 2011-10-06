package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
        this(new RankingPanel("contentPanel"));
    }

    public Home(final Panel contentPanel) {
        super();
        this.initializeWithPanel(contentPanel);
    }

    private void initializeWithPanel(final Panel contentPanel) {
        this.add(contentPanel);
        contentPanel.setOutputMarkupId(true);
        this.add(new Sidebar("sidebar"));
        this.add(new BookSearchPanel("searchbar"));
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
