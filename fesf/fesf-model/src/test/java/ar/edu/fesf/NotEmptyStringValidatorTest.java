package ar.edu.fesf;

import org.junit.Test;

import ar.edu.fesf.validations.NotEmptyStringValidator;
import ar.edu.fesf.validations.UserException;

public class NotEmptyStringValidatorTest {

    @Test(expected = UserException.class)
    public void validateWhenIsEmpty() {
        NotEmptyStringValidator.validate("", "Test");
    }

}
