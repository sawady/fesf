package ar.edu.fesf.controllers;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.MixedParamHybridUrlCodingStrategy;

public class MounterURL {

    private static final long serialVersionUID = 1L;

    private WebApplication webApplication;

    /**
     * @param webApplication
     */
    public MounterURL(final WebApplication webApplication) {
        this.setWebApplication(webApplication);
    }

    /**
     * @param mountPath
     *            Mount path (not empty)
     * @param pageClass
     *            Class of mounted page (not null)
     * @param parameters
     *            The parameter names (not null)
     */
    public void mount(final String mountPath, final Class<? extends WebPage> pageClass, final String... parameters) {
        MixedParamHybridUrlCodingStrategy urls = new MixedParamHybridUrlCodingStrategy(mountPath, pageClass, parameters);
        this.getWebApplication().mount(urls);
    }

    /**
     * @param mountPath
     *            Mount path (not empty)
     * @param pageClass
     *            Class of mounted page (not null)
     * @param redirectOnBookmarkableRequest
     *            Whether after hitting the page with URL in bookmarkable form it should be redirected to hybrid URL -
     *            needed for ajax to work properly after page refresh
     * @param parameters
     *            The parameter names (not null)
     */
    public void mount(final String mountPath, final Class<? extends WebPage> pageClass,
            final boolean redirectOnBookmarkableRequest, final String... parameters) {
        MixedParamHybridUrlCodingStrategy urls = new MixedParamHybridUrlCodingStrategy(mountPath, pageClass,
                redirectOnBookmarkableRequest, parameters);
        this.getWebApplication().mount(urls);
    }

    private void setWebApplication(final WebApplication webApplication) {
        this.webApplication = webApplication;
    }

    private WebApplication getWebApplication() {
        return this.webApplication;
    }
}