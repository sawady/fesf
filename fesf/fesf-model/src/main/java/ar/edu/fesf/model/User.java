package ar.edu.fesf.model;

import java.util.List;

import ar.edu.fesf.application.Entity;

public class User extends Entity {

    private int age;

    private String address;

    private String phone;

    private String email;

    private List<Category> categories;

    private LoggingInfo loggingInfo;

    private List<Loan> loans;

    private List<Reservation> reservations;

    /* Accessors */
    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final List<Category> categories) {
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
