package ar.edu.fesf.builders;

import org.joda.time.DateTime;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;

public class LoanBuilder {

    protected Person person;

    private int maxLoanPeriodInDays;

    private DateTime agreedReturnDate;

    private BookCopy bookCopy;

    public LoanBuilder withPerson(final Person aperson) {
        this.person = aperson;
        return this;
    }

    public LoanBuilder withMaxLoanPeriodInDays(final int amaxLoanPeriodInDays) {
        this.maxLoanPeriodInDays = amaxLoanPeriodInDays;
        return this;
    }

    public LoanBuilder withAgreedReturnDate(final DateTime aagreedReturnDate) {
        this.agreedReturnDate = aagreedReturnDate;
        return this;
    }

    public LoanBuilder withBookCopy(final BookCopy bookcopy) {
        this.bookCopy = bookcopy;
        return this;
    }

    public Loan build() {
        return new Loan(this.person, this.maxLoanPeriodInDays, this.agreedReturnDate, this.bookCopy);
    }

    /* Accesors */

    public void setPerson(final Person person) {
        this.person = person;
    }

    public void setMaxLoanPeriodInDays(final int maxLoanPeriodInDays) {
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
    }

    public void setAgreedReturnDate(final DateTime agreedReturnDate) {
        this.agreedReturnDate = agreedReturnDate;
    }

    public void setBookCopy(final BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Person getPerson() {
        return this.person;
    }

    public int getMaxLoanPeriodInDays() {
        return this.maxLoanPeriodInDays;
    }

    public DateTime getAgreedReturnDate() {
        return this.agreedReturnDate;
    }

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

}
