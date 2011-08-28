package ar.edu.fesf.validations;

public class NotEmptyStringValidator {

    private NotEmptyStringValidator() {
    }

    public static void validate(final String toValidate, final String fieldName) {
        if (toValidate.isEmpty()) {
            throw new UserException(fieldName + " cannot be empty");
        }
    }

}
