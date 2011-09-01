package ar.edu.fesf.application;

import ar.edu.fesf.model.BookInfo;
import ar.edu.fesf.model.Ranking;

/**
 * TODO: description
 */
public class Library extends Application {

    private Ranking ranking = new Ranking();

    private int maxLoanPeriodInDays = 60;

    public Library(final HomeFactory factory) {
        super(factory);
    }

    public Ranking getRanking() {
        return this.ranking;
    }

    public void setRanking(final Ranking ranking) {
        this.ranking = ranking;
    }

    public void updateRecents(final BookInfo book) {
        this.getRanking().addToRecents(book);
    }

    public void updateRanking(final BookInfo book) {
        this.getRanking().updateRanking(book);
    }

    public int getMaxLoanPeriodInDays() {
        return this.maxLoanPeriodInDays;
    }

    public void setMaxLoanPeriodInDays(final int maxLoanPeriodInDays) {
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
    }

}
