package ar.edu.fesf.model;

import java.util.List;

/**
 * TODO: description
 */
public class BookCopy {
    private String observation;

    private List<Loan> loans;

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

}
