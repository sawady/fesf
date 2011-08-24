package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.UserException;

public class BookCopy extends Entity {

    private String observations;

    private List<Loan> loans = new ArrayList<Loan>();

    /* Methods */
    public boolean isAvailable() {
        return this.getLoans().isEmpty() || this.lastLoan().hasFinished();
    }

    public void addLoan(final Loan loan) {
        if (!this.getLoans().isEmpty() && !this.lastLoan().hasFinished()) {
            throw new UserException("Last loan has not finished");
        }
        this.getLoans().add(0, loan);
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

}
