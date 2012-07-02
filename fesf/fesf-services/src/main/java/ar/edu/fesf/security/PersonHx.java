package ar.edu.fesf.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.InetOrgPerson;

public class PersonHx extends InetOrgPerson {

	private static final long serialVersionUID = 1L;

	private String givenName;

	private InetOrgPerson initOrgPerson;

	private String streetAddress;

	public static UserDetails newInstance(DirContextOperations ctx, InetOrgPerson inetOrgPerson) {
		PersonHx personHx = new PersonHx(inetOrgPerson);
		personHx.givenName = ctx.getStringAttribute("givenName");
		personHx.streetAddress = ctx.getStringAttribute("streetAddress");
		return personHx;
	}
	
	public PersonHx(InetOrgPerson inetOrgPerson) {
		this.initOrgPerson = inetOrgPerson;
	}

	public String getFirstName() {
		return this.givenName;
	}
	
	@Override
	public String getStreet() {
		return this.streetAddress;
	}
	
	@Override
	public String getSn() {
		return this.initOrgPerson.getSn();
	}
	
	@Override
	public String getTelephoneNumber() {
		return this.initOrgPerson.getTelephoneNumber();
	}
	
	@Override
	public String getMail() {
		return this.initOrgPerson.getMail();
	}
	
	@Override
	public String getUsername() {
		return this.initOrgPerson.getUsername();
	}

}
