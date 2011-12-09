package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.exceptions.MyEmailException;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.ReservationEvent;

import com.google.common.collect.Lists;

@Service
public class EmailService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoaningService loaningService;

    @Transactional(readOnly = true)
    @Secured("ROLE_LIBRARIAN")
    public void sendEmailTo(final String subject, final String body, final List<Person> persons) {

        final List<InternetAddress> emails = new ArrayList<InternetAddress>();

        try {
            for (Person aPerson : persons) {
                emails.add(new InternetAddress(EmailService.this.personService.findByEquality(aPerson).getEmail()
                        .getValue()));
            }
        } catch (AddressException e) {
            throw new MyEmailException(e.getMessage());
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    final Email email = new SimpleEmail();
                    email.setMsg(body);
                    email.setFrom("fesf.library@gmail.com");
                    email.setTo(emails);
                    email.setAuthentication("fesf.library@gmail.com", "fesffesf");
                    email.setSubject(subject);
                    email.setHostName("smtp.gmail.com");
                    email.setSmtpPort(587);
                    email.setTLS(true);
                    email.send();
                } catch (EmailException e) {
                    throw new MyEmailException(e.getMessage());
                }
            }
        }.start();
    }

    @Transactional(readOnly = true)
    @Secured("ROLE_LIBRARIAN")
    public void sendEmailToLoanees(final List<Person> persons) {
        for (Person person : persons) {
            Person personDB = this.personService.findByEquality(person);
            List<Loan> expiredLoans = personDB.expiredLoans();

            StringBuffer bodyBuffer = new StringBuffer();
            bodyBuffer.append("Hi, ");
            bodyBuffer.append(person.getName());
            bodyBuffer
                    .append(". We must inform you that some of your loans have expired. The list of them is the following: \n\n");

            for (Loan loan : expiredLoans) {
                bodyBuffer.append(this.convertToEmailBodyBook(loan.getBook()));
                bodyBuffer.append("Reserved on: ");
                bodyBuffer.append(loan.getDate().toLocalDate());
                bodyBuffer.append("\n");
                bodyBuffer.append("Agreed Date: ");
                bodyBuffer.append(loan.getAgreedReturnDate().toLocalDate());
                bodyBuffer.append("\n\n");
            }

            this.sendEmailTo("Please return these books inmediatly! /n FESF Library", bodyBuffer.toString(),
                    Lists.newArrayList(personDB));

        }
    }

    @Transactional(readOnly = true)
    public void adviceFirstWaitingUser(final ReservationEvent reservationEvent) {
        this.sendEmailTo("Interested book is available now!", this.convertToEmailBodyBook(reservationEvent.getBook()),
                Lists.newArrayList(reservationEvent.getPerson()));
    }

    @Transactional(readOnly = true)
    @Secured("ROLE_USER")
    public void sendEmailCustom(final List<Book> books, final Person person) {

        StringBuffer bodyBuffer = new StringBuffer();

        for (Book book : books) {
            bodyBuffer.append(this.convertToEmailBodyBook(book));
            bodyBuffer.append("\n\n\n");
        }

        List<Person> persons = new ArrayList<Person>(1);
        persons.add(person);

        this.sendEmailTo("List of selected Books " + new DateTime().toLocalDate(), bodyBuffer.toString(), persons);
    }

    private String convertToEmailBodyBook(final Book book) {
        Book bookDB = this.bookService.findByEquality(book);

        StringBuffer result = new StringBuffer();

        // TODO falta agregar todos los campos.

        result.append("Title: ");
        result.append(bookDB.getTitle());
        result.append("\n");

        result.append("ISBN: ");
        result.append(bookDB.getIsbn());
        result.append("\n");

        result.append("Available? ");
        result.append(bookDB.getAvailable());
        result.append("\n");

        result.append("Authors: ");
        for (Author author : bookDB.getAuthors()) {
            result.append(author.getName() + ". ");
        }
        result.append("\n");

        result.append("Categories: ");
        for (Category category : bookDB.getCategories()) {
            result.append(category.getName() + ". ");
        }
        result.append("\n");

        result.append("Description: ");
        result.append(bookDB.getDescription());
        result.append("\n");

        return result.toString();
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

}
