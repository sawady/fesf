package ar.edu.fesf.model.rules;

import static com.google.common.base.Preconditions.checkState;
import ar.edu.fesf.model.Person;

public class LoansPerUserRule implements Rule<Person> {
    @Override
    public void apply(final Person person) {
        checkState(person.getCurrentLoans().size() < 3, "Users cannot borrow more than 3 books.");
    }
}
