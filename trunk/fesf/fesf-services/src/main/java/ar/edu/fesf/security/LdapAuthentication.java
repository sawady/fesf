package ar.edu.fesf.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LdapAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	private Authentication authentication;

	private UserDetails userDetail;

	public LdapAuthentication(Authentication authentication) {
		this.authentication = authentication;
		this.userDetail = new UserDetailsLdapImpl(this.authentication.getPrincipal());
	}

	@Override
	public String getName() {
		return this.authentication.getName();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.userDetail.getAuthorities();
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
		return this.userDetail;
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
