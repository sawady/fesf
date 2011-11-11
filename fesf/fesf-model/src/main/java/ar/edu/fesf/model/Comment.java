package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class Comment extends Event {

    private static final long serialVersionUID = 1L;

    private String body;

    private Calification calification;

    protected Book book;

    public Comment() {
        super();
    }

    public Comment(final String text, final Integer califNum, final Book book, final Person person) {
        super();
        this.body = text;
        this.calification = new Calification(califNum);
        this.book = book;
        this.person = person;
    }

    /* Accessors */

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        checkNotNull(body);
        this.body = body;
    }

    public void setCalification(final Calification calification) {
        this.calification = calification;
    }

    public Calification getCalification() {
        return this.calification;
    }

    @Override
    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        checkNotNull(book);
        this.book = book;
    }

}
