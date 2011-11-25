package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

public class Book extends Entity {

    private static final long serialVersionUID = 6298319531111903588L;

    private boolean available = true;

    private String title;

    private ISBN isbn;

    private Publisher publisher;

    private String imagepath;

    private DateTime acquisitionDate = new DateTime();

    private String description = "";

    private List<Author> authors = new ArrayList<Author>();

    private int countOfLouns = 0;

    private List<BookCopy> registedCopies = new ArrayList<BookCopy>();

    private List<BookCopy> availableCopies = new ArrayList<BookCopy>();

    private Set<Category> categories = new HashSet<Category>();

    private Set<Person> loanees = new HashSet<Person>();

    private List<ReservationEvent> reservationEvents = new ArrayList<ReservationEvent>();

    private UserFeedbackManager userFeedbackManager = new UserFeedbackManager();

    public Book(final String title, final ISBN isbn, final Publisher publisher, final String imagepath,
            final String description, final List<Author> authors, final Set<Category> categories,
            final int countOfCopies, final Boolean aVailable) {
        super();
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.imagepath = imagepath;
        this.description = description;
        this.authors = authors;
        this.categories = categories;
        this.available = aVailable;

        for (Author author : authors) {
            author.addBook(this);
        }

        for (Category category : categories) {
            category.addBook(this);
        }

        this.addCopies(countOfCopies);
    }

    public Book() {
        super();
    }

    /* Methods */
    public void addCategory(final Category category) {
        this.getCategories().add(category);
        category.addBook(this);
    }

    public void addAuthor(final Author author) {
        this.getAuthors().add(author);
        author.addBook(this);
    }

    public void addCopies(final int cant) {
        for (int n = 0; n < cant; n++) {
            this.addCopy();
        }
    }

    public Integer getCountOfAvailableCopies() {
        return this.getAvailableCopies().size();
    }

    public Integer getCountOfRegisterdCopies() {
        return this.getRegistedCopies().size();
    }

    public void addCopy() {
        BookCopy newCopy = new BookCopy();
        newCopy.setBook(this);
        this.getRegistedCopies().add(newCopy);
        this.getAvailableCopies().add(newCopy);
    }

    public boolean hasAvailableCopy() {
        return !this.getAvailableCopies().isEmpty();
    }

    public BookCopy getAvailableCopy() {
        checkState(this.hasAvailableCopy(), "There are no available copies of this book");
        return this.getAvailableCopies().remove(0);
    }

    public void incrementCountOfLoans() {
        this.setCountOfLouns(this.getCountOfLouns() + 1);
    }

    public void returnCopy(final BookCopy copy) {
        this.getAvailableCopies().add(copy);
        copy.freeCopy();
    }

    public boolean isInTheReservationList(final Person person) {
        Iterator<ReservationEvent> it = this.getReservationEvents().iterator();
        boolean isInTheList = false;

        while (it.hasNext() && !isInTheList) {
            isInTheList = it.next().getPerson().getEmail().getValue().equals(person.getEmail().getValue());
        }

        return isInTheList;
    }

    public void addReservationEvent(final ReservationEvent reservation) {
        this.getReservationEvents().add(reservation);
        reservation.setBook(this);
    }

    public int getReservationAmount() {
        return this.getReservationEvents().size();
    }

    public ReservationEvent getReservationEventToInformAvailability() {
        checkState(!this.getReservationEvents().isEmpty(), "No interested users are in the queue");
        ReservationEvent res = this.getReservationEvents().get(0);
        this.getReservationEvents().remove(0);
        return res;
    }

    public void addLoanee(final Person person) {
        this.getLoanees().add(person);
    }

    public void addComment(final Comment comment) {
        this.getUserFeedbackManager().addComment(comment);
    }

    /* Accessors */

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
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

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
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

    public List<ReservationEvent> getReservationEvents() {
        return this.reservationEvents;
    }

    public void setReservationEvents(final List<ReservationEvent> reservationEvents) {
        this.reservationEvents = reservationEvents;
    }

    public List<BookCopy> getRegistedCopies() {
        return this.registedCopies;
    }

    public void setRegistedCopies(final List<BookCopy> registedCopies) {
        this.registedCopies = registedCopies;
    }

    public List<BookCopy> getAvailableCopies() {
        return this.availableCopies;
    }

    public void setAvailableCopies(final List<BookCopy> availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setAcquisitionDate(final DateTime acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public DateTime getAcquisitionDate() {
        return this.acquisitionDate;
    }

    public void setLoanees(final Set<Person> loanees) {
        this.loanees = loanees;
    }

    public Set<Person> getLoanees() {
        return this.loanees;
    }

    public void setUserFeedbackManager(final UserFeedbackManager userFeedbackManager) {
        this.userFeedbackManager = userFeedbackManager;
    }

    public UserFeedbackManager getUserFeedbackManager() {
        return this.userFeedbackManager;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return this.available;
    }

}
