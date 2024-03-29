package ar.edu.fesf.services;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.dtos.BorrowItDTO;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.others.GenericTransactionalRepositoryService;
import ar.edu.fesf.repositories.LoanRepository;

@Service
public class LoaningService extends GenericTransactionalRepositoryService<Loan> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @Autowired
    private EmailService emailService;

    /* Methods */

    @Transactional(readOnly = true)
    public Loan initializeLoanInfo(final Loan loan) {
        Loan loanDB = this.initializeFields(loan, "bookCopy");
        this.initialize(loanDB.getBookCopy().getBook(), Book.class);
        return loanDB;
    }

    private DateTime parseDate(final String date) {
        DateTime result = null;

        try {
            result = DateTime.parse(date);
        } catch (IllegalArgumentException e) {
            result = DateTime.parse(date, DateTimeFormat.forPattern("dd/MM/yy"));
        }

        return result;

    }

    @Transactional
    public void registerLoan(final Person person, final BorrowItDTO borrowItInfo, final Book book) {
        // try {
        Person personDB = this.getPersonService().findByEquality(person);
        Book bookDB = this.getBookService().findByEquality(book);

        DateTime agreedReturnDate = this.parseDate(borrowItInfo.getAgreedReturnDate());

        // TODO deshardcodear el 60
        Loan newLoan = new Loan(personDB, 60, agreedReturnDate, this.getBookService().getAvailableCopy(bookDB));
        // } catch (RuntimeException e) {
        // throw new NoAvailableBookCopyException(e.getMessage());
        // }
        this.save(newLoan);
    }

    @Transactional
    public void registerBookReturn(final Loan loan) {
        Loan newLoan = this.findById(loan.getId());
        newLoan.setFinished();
        this.save(newLoan);
        Book book = newLoan.getBook();
        if (book.isReserved()) {
            this.emailService.adviceFirstWaitingUser(book.getReservationEventToInformAvailability());
        }
        this.bookService.save(book);
    }

    @Transactional(readOnly = true)
    public List<Person> getLoanees() {
        return this.getPersonService().getLoanees();
    }

    @Transactional(readOnly = true)
    public List<Person> findBastardLoanees() {
        return this.getLoanRepository().findBastardLoanees();
    }

    private LoanRepository getLoanRepository() {
        return (LoanRepository) this.getRepository();
    }

    /* Accessors */

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    public EmailService getEmailService() {
        return this.emailService;
    }
}
