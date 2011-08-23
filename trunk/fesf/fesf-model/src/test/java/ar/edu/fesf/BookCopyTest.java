package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.validations.UserException;

/**
 * BookCopy Test
 */
public class BookCopyTest {

    private Loan loanMock;

    private Loan lastLoanMock;

    private BookCopy bookExample;

    @Before
    public void setUp() {
        this.loanMock = mock(Loan.class);
        this.lastLoanMock = mock(Loan.class);
        when(this.loanMock.hasFinished()).thenReturn(true);
        when(this.lastLoanMock.hasFinished()).thenReturn(false);

        this.bookExample = new BookCopy();
    }

    @Test(expected = UserException.class)
    public void addTwoLoansLastNotFinished() {
        when(this.loanMock.hasFinished()).thenReturn(false);
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
    }

    @Test
    public void addTwoLoansLastHasFinished() {
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
        assertEquals("Length of list must be 2", this.bookExample.getLoans().size(), 2);
        assertTrue("Loans must containt this Loan", this.bookExample.getLoans().contains(this.loanMock));
        assertTrue("Loans must containt this Loan", this.bookExample.getLoans().contains(this.lastLoanMock));
    }

    @Test
    public void lastLoan() {
        this.bookExample.addLoan(this.loanMock);
        this.bookExample.addLoan(this.lastLoanMock);
        assertEquals("Last loan must be equal to last loan added", this.bookExample.lastLoan(), this.lastLoanMock);
    }

    @Test
    public void isAvailableCaseEmptyLoans() {
        assertTrue("When Empty Loans the bookcopy must be available", this.bookExample.isAvailable());
    }

    @Test
    public void isAvailableCaseLastLoanFinished() {
        this.bookExample.addLoan(this.loanMock);
        assertTrue("When not empty and last loan finished, bookcopy must be available", this.bookExample.isAvailable());
    }

    @Test
    public void isNotAvailableCaseLastLoanNotFinished() {
        this.bookExample.addLoan(this.lastLoanMock);
        assertFalse("When last loan has not finished, bookcopy must not be available", this.bookExample.isAvailable());
    }

}
