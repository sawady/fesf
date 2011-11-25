package ar.edu.fesf.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import ar.edu.fesf.model.Role;

public class FakeAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;

    @Override
    public String getName() {
        return "Fede";
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(2);
        list.add(new GrantedAuthorityImpl(Role.ROLE_USER.toString()));
        list.add(new GrantedAuthorityImpl(Role.ROLE_LIBRARIAN.toString()));
        return list;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return new UserDetailsImpl("Fede", "Federico", "Sawady", "sawady.faso@gmail.com", "ES");
    }

    @Override
    public Object getPrincipal() {
        return this.getDetails();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        // FAKE
    }

}
