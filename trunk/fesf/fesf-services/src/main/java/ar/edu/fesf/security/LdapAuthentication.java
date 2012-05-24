package ar.edu.fesf.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class LdapAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	private Authentication authentication;

	public LdapAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public String getName() {
		return this.authentication.getName();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authentication.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return this.authentication.getCredentials();
	}

	@Override
	public Object getDetails() {
		return this.authentication.getDetails();
	}

	@Override
	public Object getPrincipal() {
		return new UserDetailsLdapImpl(this.authentication.getPrincipal());
	}

	@Override
	public boolean isAuthenticated() {
		return this.authentication.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authentication.setAuthenticated(isAuthenticated);
	}

}
