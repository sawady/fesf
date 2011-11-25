package ar.edu.fesf.exceptions;

public class TransactionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TransactionException(final String string) {
        super(string);
    }

}
