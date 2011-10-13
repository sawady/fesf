package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

public abstract class Event extends Entity {

    private static final long serialVersionUID = 1L;

    protected Person person;

    private DateTime date = new DateTime();

    /* Methods */
    public void updateUserCategories() {
        this.getPerson().getCategories().addAll(this.getBook().getCategories());
    }

    public abstract Book getBook();

    /* Accessors */
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person person) {
        checkNotNull(person);
        this.person = person;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

}
