package ar.edu.fesf;

import org.junit.Test;

import ar.edu.fesf.validations.PositiveNumberValidator;
import ar.edu.fesf.validations.UserException;

public class PositiveNumberValidatorTest {

    @Test(expected = UserException.class)
    public void validateWhenIsNegative() {
        PositiveNumberValidator.validate(-1, "Test");
    }

}
