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
    public void registerLoan(final Loan loan, final Book book) {
        try {
            loan.assignCopy(this.getBookService().getAvailableCopy(book));
        } catch (RuntimeException e) {
            throw new NoAvailableBookCopyException(e.getMessage());
        }
        this.save(loan);
    }

    @Transactional
    public void registerBookReturn(final Loan loan) {
        loan.setFinished();
    }

    @Transactional
    public List<Person> getLoanees() {
        return this.getPersonService().getLoanees();
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
