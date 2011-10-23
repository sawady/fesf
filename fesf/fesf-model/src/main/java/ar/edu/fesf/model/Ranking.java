package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ranking extends Entity {

    private static final long serialVersionUID = 1L;

    private List<Book> top20 = new ArrayList<Book>();

    private List<Book> recentlyAvailable = new ArrayList<Book>();

    private int limitOfRecentlyBooks = 20;

    /* Methods */

    /**
     * Includes a recently added book into the recentlyAvailable list, removing the oldest addition.
     */
    public void addToRecents(final Book book) {
        if (this.getLimitOfRecentlyBooks() == this.getRecentlyAvailable().size()) {
            this.getRecentlyAvailable().remove(this.getLimitOfRecentlyBooks() - 1);
        }

        this.getRecentlyAvailable().add(0, book);
    }

    public void updateRanking(final Book newBook) {

        boolean wasAdded = false;
        int addToIndex = 0;
        Iterator<Book> it = this.getTop20().iterator();

        // recorre para ponerlo en posicion
        while (it.hasNext() && !wasAdded) {
            if (newBook.getCountOfLouns() > it.next().getCountOfLouns()) {
                this.getTop20().add(addToIndex, newBook);
                wasAdded = true;
            } else {
                addToIndex++;
            }
        }

        // Si el libro ya estaba en un lugar posterior del ranking y hay lugar todavía
        if (addToIndex + 1 < this.getTop20().size()) {
            for (Book book : this.getTop20().subList(addToIndex + 1, this.getTop20().size())) {
                if (book.equals(newBook)) {
                    this.getTop20().remove(addToIndex);
                    break;
                }
                addToIndex++;
            }
        }

        // si no lo inserto pero la lista tiene espacio
        if (20 > this.getTop20().size() && !wasAdded) {
            this.getTop20().add(newBook);
        }

        // si lo inserto y la lista sobrepaso su limite
        if (20 < this.getTop20().size() && wasAdded) {
            this.getTop20().remove(this.getLimitOfRecentlyBooks() - 1);
        }

    }

    /* Accessors */

    public List<Book> getTop20() {
        return this.top20;
    }

    public void setTop20(final List<Book> top20) {
        this.top20 = top20;
    }

    public List<Book> getRecentlyAvailable() {
        return this.recentlyAvailable;
    }

    public void setRecentlyAvailable(final List<Book> recentlyAvailable) {
        this.recentlyAvailable = recentlyAvailable;
    }

    public int getLimitOfRecentlyBooks() {
        return this.limitOfRecentlyBooks;
    }

    public void setLimitOfRecentlyBooks(final int limitOfRecentlyBooks) {
        this.limitOfRecentlyBooks = limitOfRecentlyBooks;
    }

}
