package ar.edu.fesf.model;

import java.util.List;

/**
 * TODO: description
 */
public class Category {
    private String name;

    private List<BookInfo> books;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<BookInfo> getBooks() {
        return this.books;
    }

    public void setBooks(final List<BookInfo> books) {
        this.books = books;
    }

}
