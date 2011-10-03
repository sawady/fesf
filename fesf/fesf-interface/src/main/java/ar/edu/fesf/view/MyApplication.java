package ar.edu.fesf.view;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class MyApplication extends AuthenticatedWebApplication {

    private ar.edu.fesf.view.MounterURL aMounterURL;

    @Override
    public void init() {
        this.aMounterURL = new MounterURL(this);
        this.addComponentInstantiationListener(new SpringComponentInjector(this));
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
