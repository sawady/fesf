package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

public class Publisher extends Entity implements Nameable {

    private static final long serialVersionUID = 1L;

    private String name;

    private List<Book> books = new ArrayList<Book>();

    /* Accessors */
    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

}
