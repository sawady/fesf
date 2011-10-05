package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.services.BookService;

public class Home extends WebPage {

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

    private void initialize() {
        // TODO poner el top 20
        // TODO poner los recently available

        // TODO terminar el buscador
        this.add(new Link<Object>("search") {
            private static final long serialVersionUID = -4296821998512737231L;

            @Override
            public void onClick() {
                this.setResponsePage(new SearchBook(Home.this.getBookService().findAll()));
            }
        });
    }
}
