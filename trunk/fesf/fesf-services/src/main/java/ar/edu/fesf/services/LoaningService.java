package ar.edu.fesf.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;

public class LoaningService extends GenericTransactionalRepositoryService<Loan> {

    private static final long serialVersionUID = 1L;

    private PersonService personService;

    private BookService bookService;

    /* Methods */
    @Transactional
    public void registerLoan(final Person person, final Loan loan, final Book book) {
        // try {
        Person personDB = this.getPersonService().findByEquality(person);
        Book bookDB = this.getBookService().findByEquality(book);
        loan.assignCopy(personDB, this.getBookService().getAvailableCopy(bookDB));

        // } catch (RuntimeException e) {
        // throw new NoAvailableBookCopyException(e.getMessage());
        // }
        this.save(loan);
    }

    @Transactional
    public void registerBookReturn(final Loan loan) {
        Loan newLoan = this.findById(loan.getId());
        newLoan.setFinished();
        this.save(newLoan);
    }

    @Transactional
    public List<Person> getLoanees() {
        return this.getPersonService().getLoanees();
    }

    @Transactional(readOnly = true)
    public Book getBook(final Loan loan) {
        return this.findById(loan.getId()).getBook();
    }

    @Transactional(readOnly = true)
    public String getBookTitle(final Loan loan) {
        return this.getRepository().findByEquality(loan).getBook().getTitle();
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

}
