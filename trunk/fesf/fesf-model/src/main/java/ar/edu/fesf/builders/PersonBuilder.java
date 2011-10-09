package ar.edu.fesf.builders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.EmailAddress;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.ReservationEvent;
import ar.edu.fesf.model.UserInfo;

public class PersonBuilder {

    private String name;

    private String surname;

    private int age;

    private String address;

    private String phone;

    private EmailAddress email;

    private Set<Category> categories = new HashSet<Category>();

    private UserInfo userInfo;

    private List<Loan> oldLoans = new ArrayList<Loan>();

    private List<Loan> currentLoans = new ArrayList<Loan>();

    private List<ReservationEvent> reservations = new ArrayList<ReservationEvent>();

    public PersonBuilder withName(final String aName) {
        this.name = aName;
        return this;
    }

    public PersonBuilder withSurname(final String aSurname) {
        this.surname = aSurname;
        return this;
    }

    public PersonBuilder withAge(final int anAge) {
        this.age = anAge;
        return this;
    }

    public PersonBuilder withAddress(final String anAddress) {
        this.address = anAddress;
        return this;
    }

    public PersonBuilder withPhone(final String aPhone) {
        this.phone = aPhone;
        return this;
    }

    public PersonBuilder withEmail(final EmailAddress anEmail) {
        this.email = anEmail;
        return this;
    }

    public PersonBuilder withCategories(final Set<Category> someCategories) {
        this.categories = someCategories;
        return this;
    }

    public PersonBuilder withUserInfo(final UserInfo anUserInfo) {
        this.userInfo = anUserInfo;
        return this;
    }

    public PersonBuilder withOldLoans(final List<Loan> someOldLoans) {
        this.oldLoans = someOldLoans;
        return this;
    }

    public PersonBuilder withCurrentLoans(final List<Loan> someCurrentLoans) {
        this.currentLoans = someCurrentLoans;
        return this;
    }

    public PersonBuilder withReservations(final List<ReservationEvent> someReservations) {
        this.reservations = someReservations;
        return this;
    }

    public Person build() {
        return new Person(this.name, this.surname, this.age, this.address, this.phone, this.email, this.categories,
                this.userInfo, this.oldLoans, this.currentLoans, this.reservations);
    }

    /**************************************** ACCESSORS ****************************************/

    public String getName() {
        return this.name;
    }

    public void setName(final String aName) {
        this.name = aName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

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

    public List<ReservationEvent> getReservations() {
        return this.reservations;
    }

    public void setReservations(final List<ReservationEvent> reservations) {
        this.reservations = reservations;
    }

}
