package ar.edu.fesf.exceptions;

public class MyEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyEmailException(final String string) {
        super(string);
    }

}
