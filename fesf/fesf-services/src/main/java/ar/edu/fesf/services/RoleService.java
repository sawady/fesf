package ar.edu.fesf.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.fesf.model.Role;
import ar.edu.fesf.model.RoleManager;



@Service
public class RoleService implements Serializable {
	
	@Autowired
    private RoleManager roleManager;

	public Role getRoleTo(String userName) {
		return this.roleManager.getRoleTo(userName);
	}
	
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

}
