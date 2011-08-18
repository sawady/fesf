package ar.edu.fesf.model;

import java.util.Date;

public class Loan {

    private Date loanDate;

    private Date loanPeriod;

    private Date returnDate;

    private BookCopy bookCopy;

    public Date getLoanDate() {
        return this.loanDate;
    }

    public void setLoanDate(final Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanPeriod() {
        return this.loanPeriod;
    }

    public void setLoanPeriod(final Date loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(final Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

    public void setBookCopy(final BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public boolean hasReturned() {
        return this.getReturnDate() != null;
    }

}
