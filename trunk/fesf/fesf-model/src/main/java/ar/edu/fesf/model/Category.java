package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends Nameable {

    private List<Book> books = new ArrayList<Book>();

    /* Accessors */
    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

}
