package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.application.Entity;

public class Ranking extends Entity {

    private List<BookInfo> top20 = new ArrayList<BookInfo>();

    private List<BookInfo> recentlyAvailable = new ArrayList<BookInfo>();

    private int limitOfRecentlyBooks = 20;

    private int minLoansForUpdate = 0;

    /* Methods */

    /**
     * Includes a recently added book into the recentlyAvailable list, removing
     * the oldest addition.
     */
    public void addToRecents(final BookInfo book) {
        if (this.getLimitOfRecentlyBooks() == this.getRecentlyAvailable().size()) {
            this.getRecentlyAvailable().remove(0);
        }

        this.getRecentlyAvailable().add(book);
    }

    /**
     * TODO, este metodos les quedo muy complejo con diferentes abstracción es y
     * NCC mas de 5. Huele a refactor
     */
    public void updateRanking(final BookInfo newBook) {
        if (newBook.getCountOfLouns() > this.getMinLoansForUpdate()) {
            List<BookInfo> newList = new ArrayList<BookInfo>();
            Boolean added = false;

            for (BookInfo bookInfo : this.getTop20()) {
                if (!added && newBook.getCountOfLouns() > bookInfo.getCountOfLouns()) {
                    newList.add(newBook);
                    newList.add(bookInfo);
                    added = true;
                } else {
                    newList.add(bookInfo);
                }
            }
            if (!added && newList.size() < 20) {
                newList.add(newBook);
            } else {
                if (newList.size() > 20) {
                    newList.remove(newList.size() - 1);
                    this.setMinLoansForUpdate(newList.get(newList.size() - 1).getCountOfLouns());
                }
            }
            this.setTop20(newList);
        }
    }

    /* Accessors */

    public List<BookInfo> getTop20() {
        return top20;
    }

    public void setTop20(final List<BookInfo> top20) {
        this.top20 = top20;
    }

    public List<BookInfo> getRecentlyAvailable() {
        return recentlyAvailable;
    }

    public void setRecentlyAvailable(final List<BookInfo> recentlyAvailable) {
        this.recentlyAvailable = recentlyAvailable;
    }

    public int getLimitOfRecentlyBooks() {
        return limitOfRecentlyBooks;
    }

    public void setLimitOfRecentlyBooks(final int limitOfRecentlyBooks) {
        this.limitOfRecentlyBooks = limitOfRecentlyBooks;
    }

    public int getMinLoansForUpdate() {
        return minLoansForUpdate;
    }

    public void setMinLoansForUpdate(final int minLoansForUpdate) {
        this.minLoansForUpdate = minLoansForUpdate;
    }

}
