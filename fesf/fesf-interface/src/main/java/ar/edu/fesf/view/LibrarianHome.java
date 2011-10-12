package ar.edu.fesf.view;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation("ADMIN")
public class LibrarianHome extends WebPage {

    public LibrarianHome() {
        super();

        this.initalize();
    }

    private void initalize() {
        this.add(new LibrarianHomeContentPanel("content"));
    }

}
