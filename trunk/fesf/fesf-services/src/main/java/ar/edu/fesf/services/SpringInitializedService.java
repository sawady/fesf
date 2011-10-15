package ar.edu.fesf.services;

import org.springframework.transaction.annotation.Transactional;

public class SpringInitializedService {

    private BookService bookService;

    private PersonService personService;

    private LoaningService loaningService;

    @Transactional
    public void initialize() {
        this.bookService.initialize();
        this.personService.initialize();
        this.loaningService.initialize();
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }
}
