package ar.edu.fesf;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.validations.BusinessDayValidator;

/**
 * LoanTest
 */
public class LoanTest {

    private Loan loanExample;

    @Before
    public void setUp() {
        this.loanExample = new Loan();
        this.loanExample.setDate(new DateTime());
        this.loanExample.setAgreedReturnDate(new DateTime().plusDays(7), mock(BusinessDayValidator.class));
        this.loanExample.setBookCopy(new BookCopy());
    }

    @Test
    public void hasntFinishedCaseNull() {
        assertFalse("Loan is not finished is has not end date", this.loanExample.hasFinished());
    }

    @Test(expected = RuntimeException.class)
    public void setReturnDateErrorCuzFutureDateIsSetted() {
        // Future date is setted
        this.loanExample.setReturnDate(new DateTime().plus(10));
    }

}
