package ar.edu.fesf.model;

public enum Role {
    ROLE_USER, ROLE_LIBRARIAN;

    public boolean isInferior(final Role role) {
        return this.compareTo(role) >= 0;
    }
}
