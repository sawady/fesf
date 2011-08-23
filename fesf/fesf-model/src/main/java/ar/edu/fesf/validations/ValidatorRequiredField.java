package ar.edu.fesf.validations;

/**
 * TODO: description
 */
public class ValidatorRequiredField {

    private ValidatorRequiredField() {
    }

    public static void validate(final Object toValidate, final String fieldName) {
        if (toValidate == null) {
            throw new UserException("The field " + fieldName + " is required");
        }
    }

}
