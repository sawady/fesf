package ar.edu.fesf.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.UserException;
import ar.edu.fesf.validations.NotEmptyStringValidator;

public class BookInfo extends Entity {

    private String title;

    private String isbn;

    private String publisher;

    private String imagepath;

    private String description = "";

    private Set<Author> authors = new HashSet<Author>();

    private int countOfLouns = 0;

    private Set<BookCopy> registedCopies = new HashSet<BookCopy>();

    private Set<BookCopy> availableCopies = new HashSet<BookCopy>();

    private Set<Category> categories = new HashSet<Category>();

    /* Methods */
    public void addCopy() {
        BookCopy newCopy = new BookCopy();
        this.getRegistedCopies().add(newCopy);
        this.getAvailableCopies().add(newCopy);
    }

    public BookCopy getAvailableCopy() {
        Iterator<BookCopy> it = this.getAvailableCopies().iterator();

        if (!it.hasNext()) {
            throw new UserException("There are no available copies of this book");
        }

        BookCopy result = it.next();
        this.getAvailableCopies().remove(result);
        return result;
    }

    public void incrementCountOfLoans() {
        this.setCountOfLouns(this.getCountOfLouns() + 1);
    }

    public void returnCopy(final BookCopy copy) {
        this.getAvailableCopies().add(copy);
    }

    /* Accessors */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        NotEmptyStringValidator.validate(title, "Title");
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
        NotEmptyStringValidator.validate(publisher, "Publisher");
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

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BookCopy> getRegistedCopies() {
        return this.registedCopies;
    }

    public void setRegistedCopies(final Set<BookCopy> registedCopies) {
        this.registedCopies = registedCopies;
    }

    public Set<BookCopy> getAvailableCopies() {
        return this.availableCopies;
    }

    public void setAvailableCopies(final Set<BookCopy> availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public int getCountOfLouns() {
        return this.countOfLouns;
    }

    public void setCountOfLouns(final int countOfLouns) {
        this.countOfLouns = countOfLouns;
    }

}
