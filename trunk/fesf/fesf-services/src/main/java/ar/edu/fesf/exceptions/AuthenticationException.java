package ar.edu.fesf.exceptions;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 6811624042739549679L;

    public AuthenticationException(final String reason) {
        super(reason);
    }

}
