package ar.edu.fesf.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import ar.edu.fesf.model.Role;

public class UserDetailsLdapImpl extends UserDetailsImpl {
	
	private static final long serialVersionUID = 1L;
	
	private PersonHx personHx;
	
	private Set<GrantedAuthority> gaL = new HashSet<GrantedAuthority>(1);

	public UserDetailsLdapImpl(Object ldapUserDetailsImpl) {
		this.personHx = (PersonHx) ldapUserDetailsImpl;
		this.gaL.add(new GrantedAuthorityImpl(Role.ROLE_USER.toString()));
        this.gaL.add(new GrantedAuthorityImpl(Role.ROLE_LIBRARIAN.toString()));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.gaL;
	}

	@Override
	public String getPassword() {
		return this.personHx.getPassword();
	}

	@Override
	public String getUsername() {
		return this.personHx.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.personHx.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.personHx.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.personHx.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.personHx.isEnabled();
	}
	
	@Override
	public String getEmail() {
        return this.personHx.getMail();
    }
	
	@Override
	public String getFirstName() {
		return this.personHx.getFirstName();
	}
	
	@Override
	public String getUserName() {
		return this.personHx.getUsername();
	}
	
	@Override
	public String getLastName() {
		return this.personHx.getSn();
	}
	
	@Override
	public String getAddress() {
		return this.personHx.getStreet();
	}
	
	@Override
	public String getPhone() {
		return this.personHx.getTelephoneNumber();
	}
	

}
