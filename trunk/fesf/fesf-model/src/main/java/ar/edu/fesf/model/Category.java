package ar.edu.fesf.model;

import java.util.HashSet;
import java.util.Set;

public class Category extends Nameable {

    private Set<Book> books = new HashSet<Book>();

    public Category(final String name) {
        super();
        this.name = name;
    }

    public void addBook(final Book book) {
        this.getBooks().add(book);
    }

    public void removeBook(final Book book) {
        this.getBooks().remove(book);
    }

    /* Accessors */
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final Set<Book> books) {
        this.books = books;
    }

}
