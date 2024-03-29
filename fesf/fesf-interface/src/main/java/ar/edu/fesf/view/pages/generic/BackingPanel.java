package ar.edu.fesf.view.pages.generic;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class BackingPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public BackingPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(this.getContentPanel("content"));
        this.add(new AjaxFallbackLink<String>("backLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                BackingPanel.this.callback(target);
            }

        });
    }

    public abstract Panel getContentPanel(final String id);

    public abstract void callback(final AjaxRequestTarget target);

}
