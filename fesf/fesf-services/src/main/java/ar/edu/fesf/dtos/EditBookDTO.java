package ar.edu.fesf.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;

public class EditBookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    protected String title;

    protected String isbn;

    protected String publisher;

    protected String description;

    protected List<String> categories = new ArrayList<String>();

    protected List<String> authors = new ArrayList<String>();

    protected boolean available;

    public EditBookDTO() {
        super();
    }

    public EditBookDTO(final Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn().getValue();
        this.publisher = book.getPublisher().getName();
        this.description = book.getDescription();
        this.available = book.getAvailable();

        for (Author author : book.getAuthors()) {
            this.authors.add(author.getName());
        }

        for (Category category : book.getCategories()) {
            this.categories.add(category.getName());
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

}
