package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.exceptions.LastLoanNotFinishedException;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Loan;

/**
 * BookCopy Test
 */
public class BookCopyTest {

    private Loan loanMock;

    private Loan lastLoanMock;

    private BookCopy bookExample;

    @Before
    public void setup() {
        this.loanMock = mock(Loan.class);
        this.lastLoanMock = mock(Loan.class);
        when(this.loanMock.hasFinished()).thenReturn(true);
        when(this.lastLoanMock.hasFinished()).thenReturn(false);

        this.bookExample = new BookCopy();
    }

    @Test(expected = LastLoanNotFinishedException.class)
    public void addTwoLoansLastNotFinished() {
        when(this.loanMock.hasFinished()).thenReturn(false);
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
    }

    @Test
    public void addTwoLoansLastHasFinished() {
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
        assertEquals(this.bookExample.getLoans().size(), 2);
        assertTrue(this.bookExample.getLoans().contains(this.loanMock));
        assertTrue(this.bookExample.getLoans().contains(this.lastLoanMock));
    }

    @Test
    public void lastLoan() {
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
        assertEquals(this.bookExample.lastLoan(), this.lastLoanMock);
    }

    @Test
    public void isAvailableCaseEmptyLoans() {
        assertTrue(this.bookExample.isAvailable());
    }

    @Test
    public void isAvailableCaseLastLoanFinished() {
        this.bookExample.addLoan(this.loanMock);
        assertTrue(this.bookExample.isAvailable());
    }

    @Test
    public void isNotAvailableCaseLastLoanNotFinished() {
        this.bookExample.addLoan(this.lastLoanMock);
        assertFalse(this.bookExample.isAvailable());
    }

}
