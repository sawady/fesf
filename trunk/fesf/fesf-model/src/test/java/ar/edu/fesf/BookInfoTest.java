package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.exceptions.UserException;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.BookInfo;

/**
 * TODO: description
 */
public class BookInfoTest {

    private BookCopy mockCopy1;

    private BookCopy mockCopy2;

    private BookInfo bookInfoExample;

    @Before
    public void setUp() {

        this.bookInfoExample = new BookInfo();

        this.mockCopy1 = mock(BookCopy.class);
        this.mockCopy2 = mock(BookCopy.class);
    }

    @Test(expected = UserException.class)
    public void hasNotHaveAvailableCopyCaseEmpty() {
        this.bookInfoExample.getAvailableCopy();
    }

    @Test(expected = UserException.class)
    public void hasNotHaveAvailableCopyCaseCopiesHasLoans() {
        when(this.mockCopy1.isAvailable()).thenReturn(false);
        when(this.mockCopy2.isAvailable()).thenReturn(false);
        this.bookInfoExample.addCopy(this.mockCopy1);
        this.bookInfoExample.addCopy(this.mockCopy2);
        this.bookInfoExample.getAvailableCopy();
    }

    @Test
    public void hasHaveAvailableCopy() {
        when(this.mockCopy1.isAvailable()).thenReturn(false);
        when(this.mockCopy2.isAvailable()).thenReturn(true);
        this.bookInfoExample.addCopy(this.mockCopy1);
        this.bookInfoExample.addCopy(this.mockCopy2);
        assertEquals("Must have an available copy", this.bookInfoExample.getAvailableCopy(), this.mockCopy2);
    }

    @Test
    public void hasHaveAvailableCopyAlternateVersion() {
        when(this.mockCopy1.isAvailable()).thenReturn(true);
        when(this.mockCopy2.isAvailable()).thenReturn(false);
        this.bookInfoExample.addCopy(this.mockCopy1);
        this.bookInfoExample.addCopy(this.mockCopy2);
        assertEquals("Must have an available copy", this.bookInfoExample.getAvailableCopy(), this.mockCopy1);
    }

    @Test
    public void addCopy() {
        this.bookInfoExample.addCopy(this.mockCopy1);
        this.bookInfoExample.addCopy(this.mockCopy2);
        assertTrue("Must have this copy", this.bookInfoExample.getCopies().contains(this.mockCopy1));
        assertTrue("Must have this copy", this.bookInfoExample.getCopies().contains(this.mockCopy2));
    }

}
