package ar.edu.fesf.model;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ar.edu.fesf.validations.BusinessDayValidator;
import ar.edu.fesf.validations.UserException;

public class Loan extends Event {

    private static int maxLoanPeriodInDays = 60;

    private DateTime agreedReturnDate;

    private DateTime returnDate;

    private BookCopy bookCopy;

    /* Methods */
    public boolean hasFinished() {
        return this.getReturnDate() != null && this.getReturnDate().isBeforeNow();
    }

    /* Accessors */

    public DateTime getReturnDate() {
        return this.returnDate;
    }

    public DateTime getAgreedReturnDate() {
        return this.agreedReturnDate;
    }

    public void setAgreedReturnDate(final DateTime agreedReturnDate) {

        BusinessDayValidator.validate(agreedReturnDate);

        if (new Period(this.getDate(), agreedReturnDate).getDays() > Loan.maxLoanPeriodInDays) {
            throw new UserException("Loans can only last " + Loan.maxLoanPeriodInDays);
        }

        this.agreedReturnDate = agreedReturnDate;
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

    public static int getMaxLoanPeriodInDays() {
        return maxLoanPeriodInDays;
    }

    public static void setMaxLoanPeriodInDays(final int maxLoanPeriodInDays) {
        Loan.maxLoanPeriodInDays = maxLoanPeriodInDays;
    }

}
