package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    public Role getAdminRole() {
        return new Role("Admin");
    }

    public Role getLibrarianRole() {
        return new Role("Librarian");
    }

    public Role getUserRole() {
        return new Role("User");
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(this.getAdminRole());
        roles.add(this.getLibrarianRole());
        roles.add(this.getUserRole());
        return roles;
    }

}
