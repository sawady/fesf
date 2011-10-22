package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.RankingService;

public class RankingPanel extends Panel {

    private static final long serialVersionUID = -7953872321445001861L;

    @SpringBean(name = "service.ranking")
    private RankingService rankingService;

    public RankingPanel(final String id, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.initialize(ajaxCallback);
    }

    private void initialize(final IAjaxCallback<Book> ajaxCallback) {
        this.add(new HorizontalBookPanel("top20", this.getRankingService().getTop20(), ajaxCallback));
        this.add(new HorizontalBookPanel("recentlyAvailable", this.getRankingService().getRecentlyAvailable(),
                ajaxCallback));
    }

    public void setRankingService(final RankingService rankingService) {
        this.rankingService = rankingService;
    }

    public RankingService getRankingService() {
        return this.rankingService;
    }
}
