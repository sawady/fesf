package ar.edu.fesf.model;

import java.util.Date;

public class Reservation {

    private Date date;

    private BookInfo book;

    private User user;

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
