package ar.edu.fesf.exceptions;

public class NoAvailableBookCopyException extends RuntimeException {

    public NoAvailableBookCopyException(final String string) {
        super(string);
    }

    private static final long serialVersionUID = 1L;

}
