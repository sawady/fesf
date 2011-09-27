package ar.edu.fesf.model.rules;

import static com.google.common.base.Preconditions.checkArgument;

public class ValidCalificationRule implements Rule<Integer> {

    @Override
    public void apply(final Integer calification) {
        checkArgument(calification > 0 && calification <= 10, "The calification must be a number between 0 and 10");
    }

}
