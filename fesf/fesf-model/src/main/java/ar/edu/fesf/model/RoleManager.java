package ar.edu.fesf.model;

import java.util.List;

public class RoleManager {

	private List<String> userNamesWithPrivileges;

	public RoleManager(List<String> userNames) {
		this.userNamesWithPrivileges = userNames;
	}

	public Role getRoleTo(String userName) {
		return (this.userNamesWithPrivileges.contains(userName)) ? Role.ROLE_LIBRARIAN : Role.ROLE_USER;
	}

}
