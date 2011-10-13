package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReservationEvent extends Event {

    private static final long serialVersionUID = 1L;

    protected Book book;

    /* Methods */
    @Override
    public void updateUserCategories() {
        this.getPerson().getCategories().addAll(this.getBook().getCategories());
    }

    /* Accessors */
    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        checkNotNull(book);
        this.book = book;
    }

}
