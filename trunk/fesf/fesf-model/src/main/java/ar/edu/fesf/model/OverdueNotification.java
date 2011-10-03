package ar.edu.fesf.model;

public class OverdueNotification {
    private Loan loan;

    private boolean wasSent = false;

    /* Methods */

    public OverdueNotification(final Loan aLoan) {
        this.loan = aLoan;
    }

    /* Accessors */

    public void setLoan(final Loan loan) {
        this.loan = loan;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setWasSent(final boolean wasSent) {
        this.wasSent = wasSent;
    }

    public boolean isWasSent() {
        return this.wasSent;
    }

}
