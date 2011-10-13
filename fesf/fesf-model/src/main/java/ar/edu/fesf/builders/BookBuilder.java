package ar.edu.fesf.builders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.ReservationEvent;

public class BookBuilder {

    private String title;

    private ISBN isbn;

    private Publisher publisher;

    private String imagepath;

    private String description = "";

    private Set<Author> authors = new HashSet<Author>();

    private int countOfLouns = 0;

    private List<BookCopy> registedCopies = new ArrayList<BookCopy>();

    private List<BookCopy> availableCopies = new ArrayList<BookCopy>();

    private Set<Category> categories = new HashSet<Category>();

    private List<ReservationEvent> reservationEvents = new ArrayList<ReservationEvent>();

    public Book build() {
        return new Book(this.title, this.isbn, this.publisher, this.imagepath, this.description, this.authors,
                this.countOfLouns, this.registedCopies, this.availableCopies, this.categories, this.reservationEvents);
    }

    public BookBuilder withTitle(final String aTitle) {
        this.title = aTitle;
        return this;
    }

    public BookBuilder withIsbn(final ISBN aIsbn) {
        this.isbn = aIsbn;
        return this;
    }

    public BookBuilder withPublisher(final Publisher apublisher) {
        this.publisher = apublisher;
        return this;
    }

    public BookBuilder withImagepath(final String aimagepath) {
        this.imagepath = aimagepath;
        return this;
    }

    public BookBuilder withDescription(final String adescription) {
        this.description = adescription;
        return this;
    }

    public BookBuilder withAuthor(final Author author) {
        this.authors.add(author);
        return this;
    }

    public BookBuilder withAuthors(final Set<Author> aauthors) {
        this.authors = aauthors;
        return this;
    }

    public BookBuilder withCountOfLouns(final int acountOfLouns) {
        this.countOfLouns = acountOfLouns;
        return this;
    }

    public BookBuilder withRegistedCopies(final List<BookCopy> aregistedCopies) {
        this.registedCopies = aregistedCopies;
        return this;
    }

    public BookBuilder withRegistedCopy(final BookCopy copy) {
        this.registedCopies.add(copy);
        return this;
    }

    public BookBuilder withAvailableCopies(final List<BookCopy> aavailableCopies) {
        this.availableCopies = aavailableCopies;
        return this;
    }

    public BookBuilder withAvailableCopy(final BookCopy copy) {
        this.availableCopies.add(copy);
        return this;
    }

    public BookBuilder withCategories(final Set<Category> acategories) {
        this.categories = acategories;
        return this;
    }

    public BookBuilder withCategory(final Category category) {
        this.categories.add(category);
        return this;
    }

    public BookBuilder withReservationEvents(final List<ReservationEvent> areservationEvents) {
        this.reservationEvents = areservationEvents;
        return this;
    }

    public BookBuilder withReservationEvent(final ReservationEvent reservationEvent) {
        this.reservationEvents.add(reservationEvent);
        return this;
    }

    /*** ACCESSORS ***/

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setIsbn(final ISBN isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    public void setImagepath(final String imagepath) {
        this.imagepath = imagepath;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setAuthors(final Set<Author> authors) {
        this.authors = authors;
    }

    public void setCountOfLouns(final int countOfLouns) {
        this.countOfLouns = countOfLouns;
    }

    public void setRegistedCopies(final List<BookCopy> registedCopies) {
        this.registedCopies = registedCopies;
    }

    public void setAvailableCopies(final List<BookCopy> availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public void setReservationEvents(final List<ReservationEvent> reservationEvents) {
        this.reservationEvents = reservationEvents;
    }

    public String getTitle() {
        return this.title;
    }

    public ISBN getIsbn() {
        return this.isbn;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public String getImagepath() {
        return this.imagepath;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public int getCountOfLouns() {
        return this.countOfLouns;
    }

    public List<BookCopy> getRegistedCopies() {
        return this.registedCopies;
    }

    public List<BookCopy> getAvailableCopies() {
        return this.availableCopies;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public List<ReservationEvent> getReservationEvents() {
        return this.reservationEvents;
    }

}
