package ar.edu.fesf.model;

public class Library {

    private Ranking ranking = new Ranking();

    // TODO esto no va aca
    private int maxLoanPeriodInDays = 60;

    public Ranking getRanking() {
        return this.ranking;
    }

    public void setRanking(final Ranking ranking) {
        this.ranking = ranking;
    }

    public void updateRecents(final Book book) {
        this.getRanking().addToRecents(book);
    }

    public void updateRanking(final Book book) {
        this.getRanking().updateRanking(book);
    }

    public int getMaxLoanPeriodInDays() {
        return this.maxLoanPeriodInDays;
    }

    public void setMaxLoanPeriodInDays(final int maxLoanPeriodInDays) {
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
    }

}
