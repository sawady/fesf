package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.joda.time.DateTime;
import org.joda.time.Period;

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

    public DateTime getAgreedReturnDate() {
        return this.agreedReturnDate;
    }

    public DateTime getReturnDate() {
        return this.returnDate;
    }

    public void setAgreedReturnDate(final DateTime agreedReturnDate, final BusinessDayValidator bsdvalidator) {

        bsdvalidator.validate(agreedReturnDate);

        checkState(new Period(this.getDate(), agreedReturnDate).getDays() <= Loan.maxLoanPeriodInDays,
                "Loans can only last " + Loan.maxLoanPeriodInDays);

        this.agreedReturnDate = agreedReturnDate;
    }

    public void setReturnDate(final DateTime returnDate) {
        checkNotNull(returnDate);
        checkArgument(returnDate.isBeforeNow(), "The date of return is a future date");
        checkArgument(returnDate.isAfter(this.getDate()), "Invalid return date");
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
