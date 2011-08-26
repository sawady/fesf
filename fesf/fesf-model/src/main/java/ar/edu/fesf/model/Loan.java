package ar.edu.fesf.model;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ar.edu.fesf.validations.UserException;

public class Loan extends Event {

    private Period loanPeriod;

    private DateTime returnDate;

    private BookCopy bookCopy;

    /* Methods */
    public boolean hasFinished() {
        return this.getReturnDate() != null && this.getReturnDate().isBeforeNow();
    }

    /* Accessors */

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
        if (returnDate != null) {
            if (returnDate.isAfterNow()) {
                throw new UserException("The date of return is a future date");
            }
            if (returnDate.isBefore(this.getDate())) {
                throw new UserException("Invalid return date");
            }
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
