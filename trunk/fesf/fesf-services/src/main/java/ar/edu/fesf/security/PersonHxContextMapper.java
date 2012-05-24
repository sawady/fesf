package ar.edu.fesf.security;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;

public class PersonHxContextMapper extends InetOrgPersonContextMapper {
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<GrantedAuthority> authorities) {
		InetOrgPerson inetOrgPerson = (InetOrgPerson) super.mapUserFromContext(ctx, username, authorities);
		return PersonHx.newInstance(ctx,inetOrgPerson);
	}

}
