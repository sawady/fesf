package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ar.edu.fesf.application.Entity;

public class Book extends Entity {

    private String title;

    private ISBN isbn;

    private Publisher publisher;

    private String imagepath;

    private String description = "";

    private Set<Author> authors = new HashSet<Author>();

    private int countOfLouns = 0;

    private Set<BookCopy> registedCopies = new HashSet<BookCopy>();

    private Set<BookCopy> availableCopies = new HashSet<BookCopy>();

    private Set<Category> categories = new HashSet<Category>();

    private List<InterestEvent> interestEvents = new ArrayList<InterestEvent>();

    /* Methods */
    public void addCopy() {
        BookCopy newCopy = new BookCopy();
        this.getRegistedCopies().add(newCopy);
        this.getAvailableCopies().add(newCopy);
    }

    public BookCopy getAvailableCopy() {
        Iterator<BookCopy> it = this.getAvailableCopies().iterator();

        checkState(it.hasNext(), "There are no available copies of this book");

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

    public void addInterestEvent(final InterestEvent reservation) {
        this.getInterestEvents().add(reservation);
    }

    public InterestEvent getInterestEventToInformAvailability() {
        checkState(!this.getInterestEvents().isEmpty(), "No interested users are in the queue");
        InterestEvent res = this.getInterestEvents().get(0);
        this.getInterestEvents().remove(0);
        return res;
    }

    /* Accessors */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        checkArgument(!title.isEmpty());
        this.title = title;
    }

    public ISBN getIsbn() {
        return this.isbn;
    }

    public void setIsbn(final ISBN isbn) {
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(final Publisher publisher) {
        checkNotNull(publisher);
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

    public List<InterestEvent> getInterestEvents() {
        return this.interestEvents;
    }

    public void setInterestEvents(final List<InterestEvent> interestEvents) {
        this.interestEvents = interestEvents;
    }

}
