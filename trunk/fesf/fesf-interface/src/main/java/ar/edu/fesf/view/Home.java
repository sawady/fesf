package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;

public class Home extends WebPage {

    private static final long serialVersionUID = 1L;

    public Home() {
        super();
        this.initialize();
    }

    private void initialize() {
        final HomeContentPanel homeContentPanel = new HomeContentPanel("contentPanel");
        this.add(homeContentPanel);
    }

}
