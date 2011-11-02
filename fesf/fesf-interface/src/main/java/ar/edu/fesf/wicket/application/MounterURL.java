package ar.edu.fesf.wicket.application;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

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
        this.webApplication.mountPage(mountPath, pageClass);
    }

    public void setWebApplication(final WebApplication webApplication) {
        this.webApplication = webApplication;
    }

    public WebApplication getWebApplication() {
        return this.webApplication;
    }
}