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

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Role;

/**
 * Simply the acces to SpringSecurityContext
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class SecurityContextHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @return the authenticated user
     */
    @Transactional(readOnly = true)
    public UserDetailsImpl getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetailsImpl) authentication.getPrincipal();
        }
        return null;
    }

    @Transactional
    public void updatedUserRole(final Role role) {
        if (this.isAuthenticatedUser()) {
            this.getAuthenticatedUser().updateRole(role);
        }
    }

    @Transactional
    public void signOut() {
        // SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * 
     * @return true if the current user is authenticated
     */
    @Transactional(readOnly = true)
    public boolean isAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
    
   @Transactional(readOnly = true)
   public void authentication(Authentication authentication) {
       SecurityContextHolder.getContext().setAuthentication(authentication);
   }

    @Transactional(readOnly = true)
    public String[] getRoles() {
        Collection<GrantedAuthority> list = this.getAuthenticatedUser().getAuthorities();
        Iterator<GrantedAuthority> listIt = list.iterator();
        String[] roles = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            roles[i] = listIt.next().getAuthority();
        }
        return roles;
    }
}
