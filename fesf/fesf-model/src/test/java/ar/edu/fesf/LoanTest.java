package ar.edu.fesf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.validations.UserException;

/**
 * LoanTest
 */
public class LoanTest {

    private Loan loanExample;

    @Before
    public void setUp() {
        this.loanExample = new Loan();
        this.loanExample.setDate(new DateTime());
        this.loanExample.setLoanPeriod(new Period(new DateTime(), new DateTime().plusDays(7)));
        this.loanExample.setBookCopy(new BookCopy());
    }

    @Test
    public void hasntFinishedCaseNull() {
        assertFalse("Loan is not finished is has not end date", this.loanExample.hasFinished());
    }

    @Test(expected = UserException.class)
    public void setReturnDateErrorCuzFutureDateIsSetted() {
        // Future date is setted
        this.loanExample.setReturnDate(new DateTime().plus(10));
    }

    @Test
    public void hasFinishedCaseToday() {
        this.loanExample.setReturnDate(new DateTime());
        assertFalse("Loan must not finished if return date is exactly now", this.loanExample.hasFinished());
    }

    @Test
    public void hasFinishedCaseBeforeToday() {
        this.loanExample.setReturnDate(new DateTime().minusDays(1));
        assertTrue("Loan must be finished if the return date was before today", this.loanExample.hasFinished());
    }
}
