package ar.edu.fesf.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.LoanBuilder;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;

public class LoaningService extends GenericTransactionalRepositoryService<Loan> {

    private static final long serialVersionUID = 1L;

    private PersonService personService;

    private BookService bookService;

    @Transactional
    public void initialize() {

        Person aperson = this.getPersonService().findAll().get(0);
        Book book = this.getBookService().findAll().get(0);

        this.save(new LoanBuilder().withAgreedReturnDate(new DateTime().plusDays(20)).withPerson(aperson)
                .withBookCopy(book).withMaxLoanPeriodInDays(60).build());

    }

    /* Methods */

    @Transactional
    public void registerLoan(final Loan loan, final Book book) {
        // try {
        // TODO este tiene que ser la persona de la session
        loan.setPerson(this.getPersonService().findAll().get(0));
        loan.assignCopy(this.getBookService().getAvailableCopy(book));
        // } catch (RuntimeException e) {
        // throw new NoAvailableBookCopyException(e.getMessage());
        // }
        this.save(loan);
    }

    @Transactional
    public void registerBookReturn(final Loan loan) {
        this.findById(loan.getId()).setFinished();
        this.save(loan);
    }

    @Transactional
    public List<Person> getLoanees() {
        return this.getPersonService().getLoanees();
    }

    @Transactional(readOnly = true)
    public Book getBook(final Loan loan) {
        return this.findById(loan.getId()).getBook();
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

    @Transactional
    public String getBookTitle(final Loan loan) {
        return this.getBook(loan).getTitle();
    }

}
