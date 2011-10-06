package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

public class NonBusinessDay extends Entity {

    private static final long serialVersionUID = 1L;

    private DateTime date;

    private String description;

    public NonBusinessDay(final DateTime date) {
        super();
        this.date = date;
        this.description = "";
    }

    public NonBusinessDay(final DateTime date, final String desString) {
        super();
        this.date = date;
        this.description = desString;
    }

    // Basura de Hibernate
    public NonBusinessDay() {
        super();
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(final DateTime date) {
        checkNotNull(date);
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
