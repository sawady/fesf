package ar.edu.fesf.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.services.AuthenticationService;

public class SignInFieldsPanel extends PanelServiceToForm<UserInfo> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AuthenticationService authenticationService;

    private IAjaxCallback<Person> ajaxCallback;

    private UserInfo userInfo;

    public SignInFieldsPanel(final String id, final UserInfo userInfo, final IAjaxCallback<Person> ajaxCallback) {
        super(id);
        this.ajaxCallback = ajaxCallback;
        this.userInfo = userInfo;
        this.initialize();
    }

    private void initialize() {
        this.add(new RequiredTextField<String>("userid"));
        this.add(new RequiredTextField<String>("pass"));
    }

    @Override
    public UserInfo getObject() {
        return this.getUserInfo();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<UserInfo> form) {
        // TODO falta autenticar verdaderamente
        Person maybePersonDB = this.getAuthenticationService().findPersonWithUserInfo(this.getUserInfo().getUserid());
        if (maybePersonDB != null) {
            ((MyWebSession) this.getSession()).setPerson(maybePersonDB);
            this.getAjaxCallback().callback(target, maybePersonDB);
        } else {
            this.error("Wrong userid or password"); // TODO no se si esto va aca
        }
    }

    /* Accessors */
    public void setAjaxCallback(final IAjaxCallback<Person> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

    public IAjaxCallback<Person> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setUserInfo(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setAuthenticationService(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }

}
