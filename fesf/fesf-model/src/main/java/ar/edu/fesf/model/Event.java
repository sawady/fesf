package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;


public abstract class Event extends Entity {

    private Person person;

    private DateTime date = new DateTime();

    private Book book;

    /* Methods */
    public void updateUserCategories() {
        this.getPerson().getCategories().addAll(this.getBook().getCategories());
    }

    /* Accessors */
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person Person) {
        checkNotNull(Person);
        this.person = Person;
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
