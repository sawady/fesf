package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.fesf.validations.PositiveNumberValidator;
import ar.edu.fesf.validations.NotNullFieldValidator;
import ar.edu.fesf.validations.NotEmptyStringValidator;

public class User extends Nameable {

    private int age;

    private String address;

    private String phone;

    private String email;

    private Set<Category> categories = new HashSet<Category>();

    private LoggingInfo loggingInfo;

    private List<Loan> loans = new ArrayList<Loan>();

    private List<Reservation> reservations = new ArrayList<Reservation>();

    /* Accessors */
    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        NotNullFieldValidator.validate(age, "Age");
        PositiveNumberValidator.validate(age, "Age");
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        NotEmptyStringValidator.validate(address, "Address");
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        NotEmptyStringValidator.validate(phone, "Phone");
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        NotEmptyStringValidator.validate(email, "Email");
        this.email = email;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public LoggingInfo getLoggingInfo() {
        return this.loggingInfo;
    }

    public void setLoggingInfo(final LoggingInfo loggingInfo) {
        this.loggingInfo = loggingInfo;
    }

    public List<Loan> getLoans() {
        return this.loans;
    }

    public void setLoans(final List<Loan> loans) {
        this.loans = loans;
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(final List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
