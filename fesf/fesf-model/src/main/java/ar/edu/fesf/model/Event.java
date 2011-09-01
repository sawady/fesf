package ar.edu.fesf.model;

import org.joda.time.DateTime;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.NotNullFieldValidator;

public abstract class Event extends Entity {

    private Person user;

    private DateTime date = new DateTime();

    private BookInfo book;

    /* Methods */
    public void updateUserCategories() {
        this.getUser().getCategories().addAll(this.getBook().getCategories());
    }

    /* Accessors */
    public Person getUser() {
        return this.user;
    }

    public void setUser(final Person user) {
        NotNullFieldValidator.validate(user, "User");
        this.user = user;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    public BookInfo getBook() {
        return this.book;
    }

    public void setBook(final BookInfo book) {
        NotNullFieldValidator.validate(book, "Book");
        this.book = book;
    }

}
