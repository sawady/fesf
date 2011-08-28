package ar.edu.fesf.validations;

public class PositiveNumberValidator {

    private PositiveNumberValidator() {
    }

    public static void validate(final int toValidate, final String fieldName) {
        if (toValidate < 0) {
            throw new UserException(fieldName + " must be positive");
        }
    }

}
