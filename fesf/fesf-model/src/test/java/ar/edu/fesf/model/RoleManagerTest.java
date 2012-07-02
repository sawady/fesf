package ar.edu.fesf.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RoleManagerTest {

	@Test
	public void userRole() {
		List<String> userNamesWithPrivileges = new ArrayList<String>();
		userNamesWithPrivileges.add("nombreUsuario");
		RoleManager roleManager = new RoleManager(userNamesWithPrivileges);
		Role role =  roleManager.getRoleTo("otroNombreUsuario");
		assertEquals(Role.ROLE_USER, role);
	}

	
	@Test
	public void librarianRole() {
		List<String> userNamesWithPrivileges = new ArrayList<String>();
		String nombreUsuario = "nombreUsuario";
		userNamesWithPrivileges.add(nombreUsuario);
		RoleManager roleManager = new RoleManager(userNamesWithPrivileges);
		Role role =  roleManager.getRoleTo(nombreUsuario);
		assertEquals(Role.ROLE_LIBRARIAN, role);
	}

}
