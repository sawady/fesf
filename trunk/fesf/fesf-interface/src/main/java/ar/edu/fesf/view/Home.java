package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;

public class Home extends WebPage {

    public Home() {
        super();
        this.initialize();
    }

    private void initialize() {
        final HomeContentPanel homeContentPanel = new HomeContentPanel("contentPanel");
        this.add(homeContentPanel);
        this.add(new AjaxFallbackLink<String>("homeLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                homeContentPanel.changeToRakingPanel().callback(target, null);
            }

        });
        this.add(new CategoriesSidebar("sidebar", homeContentPanel.changeToResultsPanel()));
        this.add(new BookSearchPanel("searchbar", homeContentPanel.changeToResultsPanel()));
    }

}
