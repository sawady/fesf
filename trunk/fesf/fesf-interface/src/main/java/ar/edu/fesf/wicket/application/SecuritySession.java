/**
 * Copyright 2011 the original author or authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package ar.edu.fesf.wicket.application;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.security.SecurityContextHelper;
import ar.edu.fesf.security.UserDetailsImpl;
import ar.edu.fesf.services.PersonService;

/**
 * Implements the authentication strategies
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class SecuritySession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private PersonService personService;

    @SpringBean(name = "service.SecurityContextHelper")
    private SecurityContextHelper securityContextHelper;

    private Person person;

    public SecuritySession(final Request request) {
        super(request);
        Injector.get().inject(this);
    }

    /**
     * @see org.apache.wicket.authentication.AuthenticatedWebSession#authenticate(java.lang.String, java.lang.String)
     */
    @Override
    public boolean authenticate(final String username, final String password) {
        // login delegated to SpringSecurity
        return false;
    }

    /**
     * @see org.apache.wicket.authentication.AuthenticatedWebSession#getRoles()
     */
    @Override
    public Roles getRoles() {
        if (this.securityContextHelper.isAuthenticatedUser()) {
            return new Roles(this.getAuthenticatedUser().getAuthorities().iterator().next().getAuthority());
        }
        return null;
    }

    public void attachPerson(final Person person) {
        this.setPerson(person);
    }

    public UserDetailsImpl getAuthenticatedUser() {
        return this.securityContextHelper.getAuthenticatedUser();
    }

    public boolean signedIn() {
        return this.securityContextHelper.isAuthenticatedUser();
    }

    public void signOutPerson() {
        this.setPerson(null);
    }

    /* Accessors */
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public SecurityContextHelper getSecurityContextHelper() {
        return this.securityContextHelper;
    }

    public void setSecurityContextHelper(final SecurityContextHelper securityContextHelper) {
        this.securityContextHelper = securityContextHelper;
    }

}