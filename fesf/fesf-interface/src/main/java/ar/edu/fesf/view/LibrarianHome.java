package ar.edu.fesf.view;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation(value = { Roles.ADMIN })
public class LibrarianHome extends WebPage {

    private static final long serialVersionUID = 1L;

    public LibrarianHome() {
        super();
        this.initalize();
    }

    private void initalize() {
        this.add(new LibrarianHomeContentPanel("content"));
    }

}
