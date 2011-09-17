package ar.edu.fesf.application;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Ranking;

public class Library {

    private HomeRepository homeRepository;

    private Ranking ranking = new Ranking();

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

    public HomeRepository getHomeRepository() {
        return this.homeRepository;
    }

    public void setHomeRepository(final HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

}
