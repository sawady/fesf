package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.application.Entity;

public class Ranking extends Entity {

    private List<BookInfo> top20 = new ArrayList<BookInfo>();

    private List<BookInfo> recentlyAvailable = new ArrayList<BookInfo>();

    private int limitOfRecentlyBooks = 20;

    public List<BookInfo> getTop20() {
        return this.top20;
    }

    public void setTop20(final List<BookInfo> top20) {
        this.top20 = top20;
    }

    public List<BookInfo> getRecentlyAvailable() {
        return this.recentlyAvailable;
    }

    public void setRecentlyAvailable(final List<BookInfo> recentlyAvailable) {
        this.recentlyAvailable = recentlyAvailable;
    }

    public int getLimitOfRecentlyBooks() {
        return this.limitOfRecentlyBooks;
    }

    public void setLimitOfRecentlyBooks(final int limitOfRecentlyBooks) {
        this.limitOfRecentlyBooks = limitOfRecentlyBooks;
    }

    public void addToRecents(final BookInfo book) {
        if (this.getLimitOfRecentlyBooks() == this.getRecentlyAvailable().size()) {
            this.getRecentlyAvailable().remove(0);
        }

        this.getRecentlyAvailable().add(book);
    }

    public void updateRanking(final BookInfo book) {

        List<BookInfo> newList = new ArrayList<BookInfo>();
        List<BookInfo> copyList = new ArrayList<BookInfo>();
        copyList.addAll(this.getTop20());

        for (BookInfo bookInfo : this.getTop20()) {
            if (book.getCountOfLouns() > bookInfo.getCountOfLouns()) {
                newList.add(book);
                break;
            } else {
                newList.add(copyList.get(0));
                copyList.remove(0);
            }
        }

        if (!copyList.isEmpty()) {
            copyList.remove(copyList.size() - 1);
        }

        newList.addAll(copyList);
    }
}
