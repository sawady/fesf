package ar.edu.fesf.model;

import java.util.Date;

/**
 * TODO: description
 */
public class Comment {
    private User user;

    private Date date;

    private String body;

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

}
