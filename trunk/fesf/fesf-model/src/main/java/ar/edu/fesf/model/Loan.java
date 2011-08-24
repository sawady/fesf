package ar.edu.fesf.model;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.UserException;

public class Loan extends Entity {

    private DateTime loanDate;

    private Period loanPeriod;

    private DateTime returnDate;

    private BookCopy bookCopy;

    private User user;

    /* Methods */
    public boolean hasFinished() {
        return this.getReturnDate() != null && this.getReturnDate().isBeforeNow();
    }

    /* Accessors */
    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public DateTime getLoanDate() {
        return this.loanDate;
    }

    public void setLoanDate(final DateTime loanDate) {
        this.loanDate = loanDate;
    }

    public Period getLoanPeriod() {
        return this.loanPeriod;
    }

    public void setLoanPeriod(final Period period) {
        this.loanPeriod = period;
    }

    public DateTime getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(final DateTime returnDate) {
        if (returnDate.isAfterNow()) {
            throw new UserException("Your date of return is a future date");
        }
        this.returnDate = returnDate;
    }

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

    public void setBookCopy(final BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

}
