package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

public class BookCopy extends Entity {

    private static final long serialVersionUID = 1L;

    private Book book;

    private String observations = "";

    private List<Loan> loans = new ArrayList<Loan>();

    /* Methods */
    public boolean isAvailable() {
        return this.getLoans().isEmpty() || this.lastLoan().hasFinished();
    }

    public void addLoan(final Loan loan) { // El posta!
        checkState(this.isAvailable(), "Last loan has not finished");
        this.getLoans().add(0, loan);
        this.getBook().incrementCountOfLoans();
    }

    public Loan lastLoan() {
        return this.getLoans().get(0);
    }

    /* Accessors */
    public String getObservations() {
        return this.observations;
    }

    public void setObservations(final String observations) {
        this.observations = observations;
    }

    public List<Loan> getLoans() {
        return this.loans;
    }

    public void setLoans(final List<Loan> loans) {
        this.loans = loans;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

}
