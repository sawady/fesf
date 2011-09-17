package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

public class Person extends Nameable {

    private int age;

    private String address;

    private String phone;

    private String email;

    private Set<Category> categories = new HashSet<Category>();

    private UserInfo loggingInfo;

    private List<Loan> oldLoans = new ArrayList<Loan>();

    private List<Loan> currentLoans = new ArrayList<Loan>();

    private List<InterestEvent> reservations = new ArrayList<InterestEvent>();

    /**
     * TODO una practica recomendada es reificar este tipo de reglas para permitir agregar, modificar y eliminar reglas
     * mas simples. Por ejemplo modelando el objeto Rule y haciendo un apply.
     */
    /* Methods */
    public void addNewLoan(final Loan loan) {
        checkState(this.getCurrentLoans().size() < 3, "Users cannot borrow more than 3 books.");
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        checkArgument(!email.isEmpty());
        this.email = email;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public UserInfo getLoggingInfo() {
        return this.loggingInfo;
    }

    public void setLoggingInfo(final UserInfo loggingInfo) {
        this.loggingInfo = loggingInfo;
    }

    public List<InterestEvent> getReservations() {
        return this.reservations;
    }

    public void setReservations(final List<InterestEvent> reservations) {
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

}
