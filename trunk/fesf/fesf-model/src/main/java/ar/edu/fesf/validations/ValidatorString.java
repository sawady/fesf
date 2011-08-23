package ar.edu.fesf.validations;

/**
 * ValidatorString validates strings with validate()
 */
public class ValidatorString {

    private ValidatorString() {
    }

    public static void validate(final String toValidate, final String fieldName) {
        if (toValidate == null || toValidate.isEmpty()) {
            throw new UserException(fieldName + " cannot be empty");
        }
    }

}
