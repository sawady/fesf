package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.fesf.validations.NotEmptyStringValidator;
import ar.edu.fesf.validations.NotNullFieldValidator;
import ar.edu.fesf.validations.PositiveNumberValidator;
import ar.edu.fesf.validations.UserException;

public class Person extends Nameable {

    private int age;

    private String address;

    private String phone;

    private String email;

    private Set<Category> categories = new HashSet<Category>();

    private LoggingInfo loggingInfo;

    private List<Loan> oldLoans = new ArrayList<Loan>();

    private List<Loan> currentLoans = new ArrayList<Loan>();

    private List<InterestedUser> reservations = new ArrayList<InterestedUser>();

    /* Methods */

    public void addNewLoan(final Loan loan) {
        if (this.getCurrentLoans().size() >= 3) {
            throw new UserException("Users cannot borrow more than 3 books.");
        }
        this.getCurrentLoans().add(loan);
    }

    public void removeCurrentLoan(final Loan loan) {
        if (this.getCurrentLoans().isEmpty()) {
            throw new UserException("The list of loans is empty.");
        }
        loan.setReturnDate(new DateTime());
        this.getOldLoans().add(loan);
        this.getCurrentLoans().remove(loan);
    }

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

    public List<InterestedUser> getReservations() {
        return this.reservations;
    }

    public void setReservations(final List<InterestedUser> reservations) {
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
