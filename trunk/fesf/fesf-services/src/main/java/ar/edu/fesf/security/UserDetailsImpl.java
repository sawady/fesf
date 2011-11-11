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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import ar.edu.fesf.model.Role;

/**
 * Provides user information
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 */
public final class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    public UserDetailsImpl() {
        super();
    }

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String language;

    private List<GrantedAuthority> gaL = new ArrayList<GrantedAuthority>(1);

    public UserDetailsImpl(final String username, final String firstName, final String lastName, final String email,
            final String language) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = username;
        this.language = language;
        // By default every signeIn user is Granted as USER
        this.gaL.add(new GrantedAuthorityImpl(Role.USER.toString()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.gaL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public List<GrantedAuthority> getGaL() {
        return this.gaL;
    }

    public void setGaL(final List<GrantedAuthority> gaL) {
        this.gaL = gaL;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

}