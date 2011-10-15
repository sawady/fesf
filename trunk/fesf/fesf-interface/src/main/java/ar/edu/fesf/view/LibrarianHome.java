package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;

public class LibrarianHome extends WebPage {

    public LibrarianHome() {
        super();

        this.initalize();
    }

    private void initalize() {
        this.add(new LibrarianHomeContentPanel("content"));
    }

}
