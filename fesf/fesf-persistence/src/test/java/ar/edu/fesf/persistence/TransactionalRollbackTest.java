package ar.edu.fesf.persistence;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.repositories.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TransactionalRollbackTest {

    private static final String MAGO_DE_TERRAMAR = "Mago de Terramar";

    @Autowired
    private BookRepository bookRepository;

    @Test
    // overrides the class-level defaultRollback setting
    @Rollback(true)
    public void modifyDatabaseWithinTransaction() {
        // logic which uses the test data and modifies database state
        this.bookRepository.save(new BookBuilder().withTitle(MAGO_DE_TERRAMAR).build());
        List<Book> booksDB = this.bookRepository.findByProperty("title", MAGO_DE_TERRAMAR);
        Book bookDB = booksDB.get(0);
        assertEquals("Must be equals " + MAGO_DE_TERRAMAR, MAGO_DE_TERRAMAR, bookDB.getTitle());
    }

    @Test
    @AfterTransaction
    public void verifyFinalDatabaseStateAfterRollback() {
        List<Book> booksDB = this.bookRepository.findAll();
        assertTrue("DB should not include any Book", booksDB.isEmpty());
    }

    public void setBookRepository(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return this.bookRepository;
    }
}
