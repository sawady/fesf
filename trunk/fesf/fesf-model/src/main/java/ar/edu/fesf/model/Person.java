package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.fesf.model.rules.LoansPerUserRule;

public class Person extends Nameable implements Serializable {

    private static final long serialVersionUID = -1117547495640219313L;

    private int age;

    private String address;

    private String phone;

    private EmailAddress email;

    private Set<Category> categories = new HashSet<Category>();

    private UserInfo userInfo;

    private List<Loan> oldLoans = new ArrayList<Loan>();

    private List<Loan> currentLoans = new ArrayList<Loan>();

    private List<ReservationEvent> reservations = new ArrayList<ReservationEvent>();

    public Person(final String name) {
        super();
        this.setName(name);
    }

    public Person() {
        super();
    }

    /* Methods */
    public void addNewLoan(final Loan loan) {
        new LoansPerUserRule().apply(this);
        this.getCurrentLoans().add(loan);
    }

    public void removeCurrentLoan(final Loan loan) {
        checkState(!this.getCurrentLoans().isEmpty(), "The list of loans is empty.");
        loan.setReturnDate(new DateTime());
        this.getOldLoans().add(loan);
        this.getCurrentLoans().remove(loan);
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

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(final UserInfo userInfo) {
        this.userInfo = userInfo;
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

    public List<Book> reservedBooks() {
        // TODO hay que listar los libros que reservo el usuario
        return null;
    }

}
