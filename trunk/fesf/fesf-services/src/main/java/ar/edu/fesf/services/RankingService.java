package ar.edu.fesf.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Ranking;

public class RankingService extends GenericTransactionalRepositoryService<Ranking> {

    private BookService bookService;

    private Ranking ranking;

    private static final long serialVersionUID = 1L;

    @Transactional(readOnly = true)
    public List<Book> getTop20() {
        Ranking newRanking = this.findByEquality(this.getRanking());
        return this.initializeFields(newRanking, "top20").getTop20();
    }

    @Transactional(readOnly = true)
    public List<Book> getRecentlyAvailable() {
        Ranking newRanking = this.findByEquality(this.getRanking());
        return this.initializeFields(newRanking, "recentlyAvailable").getRecentlyAvailable();
    }

    @Transactional
    public void updateRanking(final Book book) {
        Ranking newRanking = this.findByEquality(this.getRanking());
        newRanking.updateRanking(book);
        this.save(newRanking);
    }

    @Transactional
    public void addToRecents(final Book book) {
        Ranking newRanking = this.findByEquality(this.getRanking());
        newRanking.updateRanking(book);
        newRanking.addToRecents(book);
        this.save(newRanking);
    }

    /* Accessors */

    public void setRanking(final Ranking ranking) {
        this.ranking = ranking;
        this.save(this.ranking);
    }

    public Ranking getRanking() {
        return this.ranking;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
