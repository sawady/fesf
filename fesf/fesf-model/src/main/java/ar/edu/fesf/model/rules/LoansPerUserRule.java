package ar.edu.fesf.model.rules;

public class LoansPerUserRule implements Rule<Integer> {
    @Override
    public boolean apply(final Integer value) {
        return value < 3;
    }
}
