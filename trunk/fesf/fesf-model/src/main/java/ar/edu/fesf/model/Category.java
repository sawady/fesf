package ar.edu.fesf.model;

import java.util.HashSet;
import java.util.Set;

public class Category extends Entity implements Nameable {

    private static final long serialVersionUID = -161649226557449750L;

    private String name;

    private Set<Book> books = new HashSet<Book>();

    public Category(final String name) {
        super();
        this.name = name;
    }

    public Category() {
        super();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void addBook(final Book book) {
        this.getBooks().add(book);
    }

    public void removeBook(final Book book) {
        this.getBooks().remove(book);
    }

    public boolean contains(final Book book) {
        return this.getBooks().contains(book);
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
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final Set<Book> books) {
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
