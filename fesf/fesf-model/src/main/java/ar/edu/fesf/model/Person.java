package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.fesf.model.rules.LoansPerUserRule;

public class Person extends Entity implements Nameable, Serializable {

    private static final long serialVersionUID = -1117547495640219313L;

    private String name;

    private String surname;

    private int age;

    private String address;

    private String phone;

    private EmailAddress email;

    private Set<Category> categories = new HashSet<Category>();

    private Role role;

    private Set<Book> loanedBooks = new HashSet<Book>();

    private List<Loan> oldLoans = new LinkedList<Loan>();

    private List<Loan> currentLoans = new LinkedList<Loan>();

    private List<ReservationEvent> reservations = new ArrayList<ReservationEvent>();

    public Person(final String name) {
        super();
        this.setName(name);
    }

    public Person() {
        super();
    }

    public Person(final String name, final String surname, final int age, final String address, final String phone,
            final EmailAddress email, final Set<Category> categories, final Role role) {
        super();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.categories = categories;
        this.role = role;
    }

    /* Methods */

    public String getFullName() {
        return this.getName() + " " + this.getSurname();
    }

    public void addNewLoan(final Loan loan) {
        new LoansPerUserRule().apply(this);
        this.getCurrentLoans().add(loan);
        this.getLoanedBooks().add(loan.getBook());
    }

    public void removeCurrentLoan(final Loan loan) {
        checkState(!this.getCurrentLoans().isEmpty(), "The list of loans is empty.");
        loan.setReturnDate(new DateTime());
        this.getOldLoans().add(loan);
        this.getCurrentLoans().remove(loan);
    }

    public boolean isLoanee() {
        return !this.getCurrentLoans().isEmpty();
    }

    public List<Book> reservedBooks() {

        List<Book> books = new ArrayList<Book>();

        for (Loan loan : this.getCurrentLoans()) {
            books.add(loan.getBook());
        }

        for (Loan loan : this.getOldLoans()) {
            books.add(loan.getBook());
        }

        return books;
    }

    public boolean cannotHaveMoreLoans() {
        return this.getCountOfCurrentLoans() >= 3;
    }

    public List<Loan> expiredLoans() {
        List<Loan> expired = new ArrayList<Loan>();

        for (Loan loan : this.getCurrentLoans()) {
            if (loan.isExpiredLoan()) {
                expired.add(loan);
            }
        }

        return expired;

    }

    /* Accessors */
    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        checkNotNull(age);
        checkArgument(age > 0);
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        checkArgument(!address.isEmpty());
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        checkArgument(!phone.isEmpty());
        this.phone = phone;
    }

    public EmailAddress getEmail() {
        return this.email;
    }

    public void setEmail(final EmailAddress email) {
        this.email = email;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public List<ReservationEvent> getReservations() {
        return this.reservations;
    }

    public void setReservations(final List<ReservationEvent> reservations) {
        this.reservations = reservations;
    }

    public List<Loan> getOldLoans() {
        return this.oldLoans;
    }

    public void setOldLoans(final List<Loan> oldLoans) {
        this.oldLoans = oldLoans;
    }

    public List<Loan> getCurrentLoans() {
        return this.currentLoans;
    }

    public void setCurrentLoans(final List<Loan> currentLoans) {
        this.currentLoans = currentLoans;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        checkNotNull(surname);
        checkArgument(!surname.isEmpty(), "Surname cannot be empty");
        this.surname = surname;
    }

    public int getCountOfCurrentLoans() {
        return this.getCurrentLoans().size();
    }

    public void setLoanedBooks(final Set<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public Set<Book> getLoanedBooks() {
        return this.loanedBooks;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

}
