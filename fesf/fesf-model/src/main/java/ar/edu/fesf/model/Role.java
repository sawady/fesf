package ar.edu.fesf.model;

public enum Role {
    USER, LIBRARIAN, ADMIN;

    public boolean isInferior(final Role role) {
        return this.compareTo(role) >= 0;
    }
}
