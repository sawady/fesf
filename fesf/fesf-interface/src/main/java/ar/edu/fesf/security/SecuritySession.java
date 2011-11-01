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
package ar.edu.fesf.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import ar.edu.fesf.model.Person;

/**
 * Implements the authentication strategies
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class SecuritySession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;

    private Person person;

    public SecuritySession(final Request request) {
        super(request);
    }

    private String[] roles;

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
        if (this.roles == null && SecurityContextHelper.isAuthenticatedUser()) {
            return new Roles(SecurityContextHelper.getAuthenticatedUser().getAuthorities().iterator().next()
                    .getAuthority());
        }
        return null;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public void setRoles(final String[] roles) {
        this.roles = roles.clone();
    }

    public void signOutPerson() {
        this.setPerson(null);
    }

}