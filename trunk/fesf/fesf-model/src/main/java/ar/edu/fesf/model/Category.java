package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends Nameable {

    private static final long serialVersionUID = -161649226557449750L;

    private List<Book> books = new ArrayList<Book>();

    public Category(final String name) {
        super();
        this.name = name;
    }

    public Category() {
        super();
    }

    public void addBook(final Book book) {
        this.getBooks().add(book);
    }

    public void removeBook(final Book book) {
        this.getBooks().remove(book);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Category other = (Category) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /* Accessors */
    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

}
