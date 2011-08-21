package ar.edu.fesf.model;

import java.util.List;

/**
 * 
 */
public class BookInfo {
    private String title;

    private String isbn;

    private String publisher;

    private String imagepath;

    private String description;

    private List<Author> authors;

    private List<BookCopy> copies;

    private List<Category> categories;

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

    public String getImagepath() {
        return this.imagepath;
    }

    public void setImagepath(final String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

    public List<BookCopy> getCopies() {
        return this.copies;
    }

    public void setCopies(final List<BookCopy> copies) {
        this.copies = copies;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

}
