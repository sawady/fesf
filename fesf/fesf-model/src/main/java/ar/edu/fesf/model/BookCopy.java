package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.validations.UserException;

/**
 * TODO: description
 */
public class BookCopy {

    private String observation = "";

    private List<Loan> loans = new ArrayList<Loan>();

    public String getObservation() {
        return this.observation;
    }

    public void setObservation(final String observation) {
        this.observation = observation;
    }

    public List<Loan> getLoans() {
        return this.loans;
    }

    public void setLoans(final List<Loan> loans) {
        this.loans = loans;
    }

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

}
