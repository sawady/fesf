package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;

public class Home extends WebPage {

    public Home() {
        super();
        this.initialize();
    }

    private void initialize() {
        final HomeContentPanel homeContentPanel = new HomeContentPanel("contentPanel");
        this.add(homeContentPanel);
        this.add(new HomeUserbarPanel("userbar", homeContentPanel.changeToRakingPanel()));
        this.add(new CategoriesSidebar("sidebar", homeContentPanel.changeToResultsPanel()));
        this.add(new BookSearchPanel("searchbar", homeContentPanel.changeToResultsPanel()));
    }

}
