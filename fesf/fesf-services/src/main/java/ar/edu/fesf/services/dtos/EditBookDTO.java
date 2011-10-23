package ar.edu.fesf.services.dtos;

import java.io.Serializable;

import ar.edu.fesf.model.Book;

public class EditBookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    protected String title;

    protected String isbn;

    protected String publisher;

    protected String description;

    public EditBookDTO() {
        super();
    }

    public EditBookDTO(final Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn().getValue();
        this.publisher = book.getPublisher().getName();
        this.description = book.getDescription();
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

}
