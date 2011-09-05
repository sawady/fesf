package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends Nameable {

    private List<BookInfo> books = new ArrayList<BookInfo>();

    /* Accessors */
    public List<BookInfo> getBooks() {
        return this.books;
    }

    public void setBooks(final List<BookInfo> books) {
        this.books = books;
    }

}
