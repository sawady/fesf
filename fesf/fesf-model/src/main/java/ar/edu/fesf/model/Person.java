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

    private LoggingInfo loggingInfo;

    private List<Loan> oldLoans = new ArrayList<Loan>();

    private List<Loan> currentLoans = new ArrayList<Loan>();

    private List<InterestEvent> reservations = new ArrayList<InterestEvent>();

    /**
     * TODO una practica recomendada es reificar este tipo de reglas para
     * permitir agregar, modificar y eliminar reglas mas simples. Por ejemplo
     * modelando el objeto Rule y haciendo un apply.
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
        return age;
    }

    public void setAge(final int age) {
        checkNotNull(age);
        checkArgument(age > 0);
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        checkArgument(!address.isEmpty());
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        checkArgument(!phone.isEmpty());
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        checkArgument(!email.isEmpty());
        this.email = email;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public LoggingInfo getLoggingInfo() {
        return loggingInfo;
    }

    public void setLoggingInfo(final LoggingInfo loggingInfo) {
        this.loggingInfo = loggingInfo;
    }

    public List<InterestEvent> getReservations() {
        return reservations;
    }

    public void setReservations(final List<InterestEvent> reservations) {
        this.reservations = reservations;
    }

    public List<Loan> getOldLoans() {
        return oldLoans;
    }

    public void setOldLoans(final List<Loan> oldLoans) {
        this.oldLoans = oldLoans;
    }

    public List<Loan> getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(final List<Loan> currentLoans) {
        this.currentLoans = currentLoans;
    }

}
