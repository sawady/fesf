package ar.edu.fesf.services;

import org.springframework.transaction.annotation.Transactional;

public class SpringInitializedService {

    private BookService bookService;

    private PersonService personService;

    @Transactional
    public void initialize() {
        this.bookService.initialize();
        this.personService.initialize();
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

}
