package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.InterestEvent;

public class BookTest {

    private Book bookExample;

    @Before
    public void setUp() {

        this.bookExample = new Book();

    }

    @Test
    public void addCopy() {
        this.bookExample.addCopy();
        this.bookExample.addCopy();

        for (BookCopy bc : this.bookExample.getRegistedCopies()) {
            assertTrue("Must have this copy", this.bookExample.getAvailableCopies().contains(bc));
        }
        for (BookCopy bc : this.bookExample.getAvailableCopies()) {
            assertTrue("Must have this copy", this.bookExample.getRegistedCopies().contains(bc));
        }

        assertEquals("Available Copies must be 2", this.bookExample.getAvailableCopies().size(), 2);
    }

    @Test(expected = RuntimeException.class)
    public void getUserToInformAvailabilityWhenNoUserIsInterested() {
        this.bookExample.getInterestEventToInformAvailability();
    }

    @Test
    public void getUserToInformAvailabilityWhenUsersAreInterested() {
        InterestEvent interestEvent1 = mock(InterestEvent.class);
        InterestEvent interestEvent2 = mock(InterestEvent.class);

        this.bookExample.addInterestEvent(interestEvent1);
        this.bookExample.addInterestEvent(interestEvent2);

        InterestEvent result = this.bookExample.getInterestEventToInformAvailability();

        assertEquals("Must be the first interest event", interestEvent1, result);
        assertTrue("The other interest event must be in the list",
                this.bookExample.getInterestEvents().contains(interestEvent2));
    }

    @Test(expected = RuntimeException.class)
    public void getAvailableCopyCaseEmpty() {
        this.bookExample.getAvailableCopy();
    }

    @Test(expected = RuntimeException.class)
    public void getAvailableCopyCaseCopiesHaveLoans() {

        this.bookExample.addCopy();
        this.bookExample.addCopy();

        this.bookExample.getAvailableCopy();
        this.bookExample.getAvailableCopy();
        this.bookExample.getAvailableCopy();
    }

    @Test
    public void getAvailableCopyWhenNormalUse() {

        this.bookExample.addCopy();

        BookCopy availableCopy = this.bookExample.getAvailableCopy();

        assertFalse("Must have an available copy", this.bookExample.getAvailableCopies().contains(availableCopy));
        assertTrue("Must have an available copy", this.bookExample.getRegistedCopies().contains(availableCopy));

    }

}
