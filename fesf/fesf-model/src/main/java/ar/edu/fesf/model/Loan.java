package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ar.edu.fesf.model.validations.BusinessDayValidator;

public class Loan extends Event {

    private static final long serialVersionUID = 1L;

    private int maxLoanPeriodInDays;

    private DateTime agreedReturnDate;

    private DateTime returnDate;

    private BookCopy bookCopy;

    /* Methods */

    public Loan() {
        super();
    }

    public Loan(final Person person, final int maxLoanPeriodInDays, final DateTime agreedReturnDate,
            final BookCopy bookCopy) {
        super();
        this.person = person;
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
        this.agreedReturnDate = agreedReturnDate;
        this.bookCopy = bookCopy;
        if (person != null && bookCopy != null) {
            this.assignCopy(person, bookCopy);
        }
    }

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

    public void setAgreedReturnDate(final DateTime date, final BusinessDayValidator bsdvalidator) {
        bsdvalidator.validate(date);

        checkState(new Period(this.getDate(), date).getDays() <= this.getMaxLoanPeriodInDays(), "Loans can only last "
                + this.getMaxLoanPeriodInDays());

        this.agreedReturnDate = date;
    }

    public void setReturnDate(final DateTime returnDate) {
        checkNotNull(returnDate);
        checkArgument(returnDate.isAfter(this.getDate()), "Invalid return date");
        this.returnDate = returnDate;
    }

    public void assignCopy(final Person person, final BookCopy bookcopy) {
        this.setBookCopy(bookcopy);
        this.getBookCopy().addLoan(this);

        this.setPerson(person);
        this.getPerson().addNewLoan(this);

        this.getBook().addLoanee(this.getPerson());

        this.updateUserCategories();
    }

    public void setFinished() {
        checkState(!this.hasFinished(), "This loan is already finished!");
        this.setReturnDate(new DateTime());
        this.getBook().returnCopy(this.getBookCopy());
        this.getPerson().removeCurrentLoan(this);
    }

    @Override
    public Book getBook() {
        return this.getBookCopy().getBook();
    }

    /* Accesors */

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

    public void setBookCopy(final BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public int getMaxLoanPeriodInDays() {
        return this.maxLoanPeriodInDays;
    }

    public void setMaxLoanPeriodInDays(final int maxLoanPeriodInDays) {
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
    }
}
