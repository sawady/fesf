package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;

public class HomeUserbarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private IAjaxCallback<?> homeLinkCallback;

    public HomeUserbarPanel(final String id, final IAjaxCallback<?> homeLinkCallback) {
        super(id);
        this.setHomeLinkCallback(homeLinkCallback);
        this.initialize();
    }

    private void initialize() {
        this.add(new AjaxFallbackLink<String>("homeLink") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                HomeUserbarPanel.this.getHomeLinkCallback().callback(target, null);
            }

        });
        this.add(new AuthenticatedUserBarPanel("authentication"));
    }

    public void setHomeLinkCallback(final IAjaxCallback<?> homeLinkCallback) {
        this.homeLinkCallback = homeLinkCallback;
    }

    public IAjaxCallback<?> getHomeLinkCallback() {
        return this.homeLinkCallback;
    }

}
