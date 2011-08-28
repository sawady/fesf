package ar.edu.fesf;

import org.junit.Test;

import ar.edu.fesf.validations.NotNullFieldValidator;
import ar.edu.fesf.validations.UserException;

public class NotNullFieldValidatorTest {

    @Test(expected = UserException.class)
    public void validateWhenIsNull() {
        NotNullFieldValidator.validate(null, "Test");
    }

}
