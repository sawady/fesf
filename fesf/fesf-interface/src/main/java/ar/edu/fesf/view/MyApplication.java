package ar.edu.fesf.view;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ar.edu.fesf.services.SpringInitializedService;

public class MyApplication extends AuthenticatedWebApplication {

    private static final long serialVersionUID = 1L;

    private ar.edu.fesf.view.MounterURL aMounterURL;

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
        this.aMounterURL = new MounterURL(this);
        this.addComponentInstantiationListener(new SpringComponentInjector(this));
        this.getSpringInitializedService().initialize();

        this.mountUrl("home", Home.class, "");
        this.mountUrl("users", HomeUser.class, "");

        // TODO agregar estas homes con sus respectivos logueos
        // this.mountUrl("librarian", HomeLibrarian.class, "");
        // this.mountUrl("admin", HomeAdmin.class, "");
    }

    private void mountUrl(final String mountPath, final Class<? extends WebPage> pageClass, final String... parameters) {
        this.aMounterURL.mount(mountPath, pageClass, parameters);
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return WebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return Home.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

    public String getContextPath() {
        return this.getServletContext().getContextPath();
    }

}
