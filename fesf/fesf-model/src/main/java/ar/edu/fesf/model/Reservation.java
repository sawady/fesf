package ar.edu.fesf.model;

import java.util.Date;

import ar.edu.fesf.application.Entity;

public class Reservation extends Entity {

    private Date date;

    private BookInfo book;

    private User user;

    /* Accessors */
    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public BookInfo getBook() {
        return this.book;
    }

    public void setBook(final BookInfo book) {
        this.book = book;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
