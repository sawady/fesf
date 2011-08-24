package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.BookInfo;
import ar.edu.fesf.validations.UserException;

/**
 * TODO: description
 */
public class BookInfoTest {

    private BookInfo bookInfoExample;

    @Before
    public void setUp() {

        this.bookInfoExample = new BookInfo();

    }

    @Test(expected = UserException.class)
    public void getAvailableCopyCaseEmpty() {
        this.bookInfoExample.getAvailableCopy();
    }

    @Test(expected = UserException.class)
    public void getAvailableCopyCaseCopiesHasLoans() {

        this.bookInfoExample.addCopy();
        this.bookInfoExample.addCopy();

        this.bookInfoExample.getAvailableCopy();
        this.bookInfoExample.getAvailableCopy();
        this.bookInfoExample.getAvailableCopy();
    }

    @Test
    public void getAvailableCopyCase() {

        this.bookInfoExample.addCopy();

        BookCopy availableCopy = this.bookInfoExample.getAvailableCopy();

        assertFalse("Must have an available copy", this.bookInfoExample.getAvailableCopies().contains(availableCopy));
        assertTrue("Must have an available copy", this.bookInfoExample.getRegistedCopies().contains(availableCopy));

    }

    @Test
    public void addCopy() {
        this.bookInfoExample.addCopy();
        this.bookInfoExample.addCopy();

        for (BookCopy bk : this.bookInfoExample.getRegistedCopies()) {
            assertTrue("Must have this copy", this.bookInfoExample.getAvailableCopies().contains(bk));
        }

        assertEquals("Available Copies must be 2", this.bookInfoExample.getAvailableCopies().size(), 2);
    }
}
