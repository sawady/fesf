package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

import ar.edu.fesf.application.Entity;

public abstract class Event extends Entity {

    private Person user;

    private DateTime date = new DateTime();

    private Book book;

    /* Methods */
    public void updateUserCategories() {
        this.getUser().getCategories().addAll(this.getBook().getCategories());
    }

    /* Accessors */
    public Person getUser() {
        return this.user;
    }

    public void setUser(final Person user) {
        checkNotNull(user);
        this.user = user;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        checkNotNull(book);
        this.book = book;
    }

}
