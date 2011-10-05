package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;

public class HomeAlternativo extends WebPage {

    public HomeAlternativo() {
        super();
        this.initialize();
    }

    private void initialize() {
        this.add(new Sidebar("sidebar"));
    }
}
