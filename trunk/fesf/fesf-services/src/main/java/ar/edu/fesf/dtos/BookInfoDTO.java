package ar.edu.fesf.dtos;

import java.util.Set;

public class BookInfoDTO {

    String title;

    private String isbn;

    private String publisher;

    private String imagepath;

    private String description = "";

    private Set<String> categories;

    private int countOfAvailableCopies;

    private int id; // para poder buscarlo

    public BookInfoDTO(final String title, final String isbn, final String publisher, final String imagepath,
            final String description, final Set<String> categories, final int countOfAvailableCopies, final int bookID) {
        super();
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.imagepath = imagepath;
        this.description = description;
        this.categories = categories;
        this.countOfAvailableCopies = countOfAvailableCopies;
        this.id = bookID;
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

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<String> categories) {
        this.categories = categories;
    }

    public int getCountOfAvailableCopies() {
        return this.countOfAvailableCopies;
    }

    public void setCountOfAvailableCopies(final int countOfAvailableCopies) {
        this.countOfAvailableCopies = countOfAvailableCopies;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

}
