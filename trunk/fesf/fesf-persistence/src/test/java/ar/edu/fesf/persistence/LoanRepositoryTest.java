package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.builders.LoanBuilder;
import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.repositories.BookRepository;
import ar.edu.fesf.repositories.LoanRepository;
import ar.edu.fesf.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    private Book book;

    private Loan loan1;

    private Loan loan2;

    private Loan loan3;

    private Person pepe;

    private Person esteban;

    @Before
    public void setUp() {

        this.pepe = new PersonBuilder().withName("Pepe").build();
        this.esteban = new PersonBuilder().withName("Esteban").build();

        this.book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(new Category("Drama"))
                .withAuthor(new Author("Pablo Funes")).withPublisher(new Publisher("Editorial Amboro"))
                .withCountOfCopies(5).withIsbn(new ISBN("12345789")).build();

        this.loan1 = new LoanBuilder().withAgreedReturnDate(new DateTime().plusDays(30)).withBookCopy(this.book)
                .withMaxLoanPeriodInDays(60).withPerson(this.pepe).build();
        this.loan2 = new LoanBuilder().withAgreedReturnDate(new DateTime().plusDays(25)).withBookCopy(this.book)
                .withMaxLoanPeriodInDays(60).withPerson(this.esteban).build();
        this.loan3 = new LoanBuilder().withAgreedReturnDate(new DateTime().plusDays(40)).withBookCopy(this.book)
                .withMaxLoanPeriodInDays(60).withPerson(this.esteban).build();

        this.loanRepository.save(this.loan1);
        this.loanRepository.save(this.loan2);
        this.loanRepository.save(this.loan3);

    }

    @Test
    public void count() {
        assertEquals("count must be 3", this.loanRepository.count(), 3);
    }

    @Test
    public void iterator() {
        for (Iterator<Loan> it = this.loanRepository.getIterator(); it.hasNext();) {
            Loan next = it.next();
            int loanID = next.getId();
            assertTrue("Must be some", loanID == this.loan1.getId() || loanID == this.loan2.getId()
                    || loanID == this.loan3.getId());
        }
    }

    @Test
    public void personLoans() {
        Person esteban2 = this.personRepository.findByPropertyUnique("name", "Esteban");
        assertEquals("Must be 2", 2, esteban2.getCountOfCurrentLoans());
        assertTrue("Must be loan2", esteban2.getCurrentLoans().contains(this.loan2));
        assertTrue("Must be loan3", esteban2.getCurrentLoans().contains(this.loan3));
        Loan loan22 = this.loanRepository.findByEquality(this.loan2);
        assertEquals("Must be esteban", esteban2, loan22.getPerson());
        assertEquals("Must be loan22", esteban2.getCurrentLoans().get(0), loan22);
    }

    @Test
    public void bookCopy() {
        Book book2 = this.bookRepository.findByPropertyUnique("title", "Un Mago de Terramar");
        assertEquals("Must be 2", 2, book2.getCountOfAvailableCopies());
        assertEquals("Must be 3", 3, book2.getCountOfLouns());
        assertEquals("Must be equals book2", book2, this.loan1.getBook());
        assertFalse("Must not contain loan1 copy", book2.getAvailableCopies().contains(this.loan1.getBookCopy()));
        assertTrue("Must contain loan1 copy", book2.getRegistedCopies().contains(this.loan1.getBookCopy()));
        assertFalse("Must not contain loan2 copy", book2.getAvailableCopies().contains(this.loan2.getBookCopy()));
        assertTrue("Must contain loan2 copy", book2.getRegistedCopies().contains(this.loan2.getBookCopy()));
        assertFalse("Must not contain loan3 copy", book2.getAvailableCopies().contains(this.loan3.getBookCopy()));
        assertTrue("Must contain loan3 copy", book2.getRegistedCopies().contains(this.loan3.getBookCopy()));
    }

    public LoanRepository getLoanRepository() {
        return this.loanRepository;
    }

    public void setLoanRepository(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public BookRepository getBookRepository() {
        return this.bookRepository;
    }

    public void setBookRepository(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public PersonRepository getPersonRepository() {
        return this.personRepository;
    }

    public void setPersonRepository(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Loan getLoan1() {
        return this.loan1;
    }

    public void setLoan1(final Loan loan1) {
        this.loan1 = loan1;
    }

    public Loan getLoan2() {
        return this.loan2;
    }

    public void setLoan2(final Loan loan2) {
        this.loan2 = loan2;
    }

    public Loan getLoan3() {
        return this.loan3;
    }

    public void setLoan3(final Loan loan3) {
        this.loan3 = loan3;
    }

    public Person getPepe() {
        return this.pepe;
    }

    public void setPepe(final Person pepe) {
        this.pepe = pepe;
    }

    public Person getEsteban() {
        return this.esteban;
    }

    public void setEsteban(final Person esteban) {
        this.esteban = esteban;
    }

}
