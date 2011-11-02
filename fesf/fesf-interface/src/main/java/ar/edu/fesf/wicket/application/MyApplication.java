package ar.edu.fesf.wicket.application;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ar.edu.fesf.services.SpringInitializedService;
import ar.edu.fesf.view.pages.home.Home;
import ar.edu.fesf.view.pages.librarian.LibrarianHome;
import ar.edu.fesf.view.pages.signin.SignIn;

public class MyApplication extends AuthenticatedWebApplication {

    private static final long serialVersionUID = 1L;

    private MounterURL aMounterURL;

    @SpringBean(name = "service.SpringInitializedService")
    private SpringInitializedService springInitializedService;

    public SpringInitializedService getSpringInitializedService() {
        return this.springInitializedService;
    }

    public void setSpringInitializedService(final SpringInitializedService springInitializedService) {
        this.springInitializedService = springInitializedService;
    }

    @Override
    public void init() {
        super.init();
        this.getMarkupSettings().setCompressWhitespace(true);
        this.getMarkupSettings().setStripWicketTags(true);

        this.setAMounterURL(new MounterURL(this));

        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.getSpringContext(), true));

        this.getSpringInitializedService().initialize();

        this.mountUrl("home", Home.class, "");
        this.mountUrl("librarian", LibrarianHome.class, "");
        this.mountUrl("login", SignIn.class, "");
    }

    private void mountUrl(final String mountPath, final Class<? extends WebPage> pageClass, final String... parameters) {
        this.getAMounterURL().mount(mountPath, pageClass, parameters);
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return SecuritySession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return SignIn.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

    /**
     * @return WebApplicationContext
     */
    public final WebApplicationContext getSpringContext() {
        return WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    public String getContextPath() {
        return this.getServletContext().getContextPath();
    }

    private void setAMounterURL(final MounterURL aMounter) {
        this.aMounterURL = aMounter;
    }

    private MounterURL getAMounterURL() {
        return this.aMounterURL;
    }

}
