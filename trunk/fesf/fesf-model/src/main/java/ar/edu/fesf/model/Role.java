package ar.edu.fesf.model;

public class Role {

    private String roleKind;

    public String getRoleKind() {
        return this.roleKind;
    }

    public Role(final String roleKind) {
        super();
        this.roleKind = roleKind;
    }

}
