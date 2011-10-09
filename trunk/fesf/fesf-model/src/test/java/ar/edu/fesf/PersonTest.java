package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;

public class PersonTest {

    // @Test(expected = IllegalArgumentException.class)
    // public void personaNameCantBeEmpty() {
    // Person person = new Person();
    // person.setName("");
    // }

    @Test
    public void addNewLoanWhenNoLoans() {
        Loan loan = mock(Loan.class);
        Person person = new Person();
        person.addNewLoan(loan);
        assertEquals("The size must be equals 1", 1, person.getCurrentLoans().size());
    }

    @Test
    public void addNewLoanWhen2Loans() {
        Loan loan1 = mock(Loan.class);
        Loan loan2 = mock(Loan.class);
        Loan loan3 = mock(Loan.class);
        Person person = new Person();
        person.addNewLoan(loan1);
        person.addNewLoan(loan2);
        person.addNewLoan(loan3);
        assertEquals("The size must be equals 3", 3, person.getCurrentLoans().size());
        assertTrue("The list must contain loan1", person.getCurrentLoans().contains(loan1));
        assertTrue("The list must contain loan2", person.getCurrentLoans().contains(loan2));
        assertTrue("The list must contain loan3", person.getCurrentLoans().contains(loan3));
    }

    @Test(expected = RuntimeException.class)
    public void addNewLoanWhen3Loans() {
        Loan loan1 = mock(Loan.class);
        Loan loan2 = mock(Loan.class);
        Loan loan3 = mock(Loan.class);
        Loan loan4 = mock(Loan.class);
        Person person = new Person();
        person.addNewLoan(loan1);
        person.addNewLoan(loan2);
        person.addNewLoan(loan3);
        person.addNewLoan(loan4);
    }

    @Test(expected = RuntimeException.class)
    public void removeCurrentLoanWhenNoLoans() {
        Loan loan = mock(Loan.class);
        Person person = new Person();

        person.removeCurrentLoan(loan);
        assertTrue("The list must be empty", person.getCurrentLoans().isEmpty());
    }

    @Test
    public void removeCurrentLoanWhen2Loans() {
        Loan loan1 = mock(Loan.class);
        Loan loan2 = mock(Loan.class);
        Person person = new Person();
        person.getCurrentLoans().add(loan1);
        person.getCurrentLoans().add(loan2);

        person.removeCurrentLoan(loan1);
        assertEquals("The size must be equals 1", 1, person.getCurrentLoans().size());
        assertTrue("The list must be contain loan2", person.getCurrentLoans().contains(loan2));
        assertTrue("The list of old loans must be contain loan1", person.getOldLoans().contains(loan1));
    }
}
