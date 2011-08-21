package ar.edu.fesf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.Loan;

/**
 * LoanTest
 */
public class LoanTest {

    private Loan loanExample;

    @Before
    public void setup() {
        this.loanExample = new Loan();
        this.loanExample.setLoanDate(new DateTime());
        this.loanExample.setLoanPeriod(new Period(new DateTime(), new DateTime().plusDays(7)));
    }

    @Test
    public void hasntFinishedCaseNull() {
        assertFalse(this.loanExample.hasFinished());
    }

    @Test
    public void hasntFinishedCaseCommingBackInFuture() {
        this.loanExample.setReturnDate(new DateTime().plus(10));
        assertFalse(this.loanExample.hasFinished());
    }

    @Test
    public void hasFinishedCaseToday() {
        this.loanExample.setReturnDate(new DateTime());
        assertFalse(this.loanExample.hasFinished());
    }

    @Test
    public void hasFinishedCaseBeforeToday() {
        this.loanExample.setReturnDate(new DateTime().minusDays(1));
        assertTrue(this.loanExample.hasFinished());
    }
}
