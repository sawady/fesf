package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.BookInfo;
import ar.edu.fesf.model.InterestEvent;

public class BookInfoTest {

    private BookInfo bookInfoExample;

    @Before
    public void setUp() {

        this.bookInfoExample = new BookInfo();

    }

    @Test
    public void addCopy() {
        this.bookInfoExample.addCopy();
        this.bookInfoExample.addCopy();

        for (BookCopy bc : this.bookInfoExample.getRegistedCopies()) {
            assertTrue("Must have this copy", this.bookInfoExample.getAvailableCopies().contains(bc));
        }
        for (BookCopy bc : this.bookInfoExample.getAvailableCopies()) {
            assertTrue("Must have this copy", this.bookInfoExample.getRegistedCopies().contains(bc));
        }

        assertEquals("Available Copies must be 2", this.bookInfoExample.getAvailableCopies().size(), 2);
    }

    @Test(expected = RuntimeException.class)
    public void getUserToInformAvailabilityWhenNoUserIsInterested() {
        this.bookInfoExample.getInterestEventToInformAvailability();
    }

    @Test
    public void getUserToInformAvailabilityWhenUsersAreInterested() {
        InterestEvent interestEvent1 = mock(InterestEvent.class);
        InterestEvent interestEvent2 = mock(InterestEvent.class);

        this.bookInfoExample.addInterestEvent(interestEvent1);
        this.bookInfoExample.addInterestEvent(interestEvent2);

        InterestEvent result = this.bookInfoExample.getInterestEventToInformAvailability();

        assertEquals("Must be the first interest event", interestEvent1, result);
        assertTrue("The other interest event must be in the list",
                this.bookInfoExample.getInterestEvents().contains(interestEvent2));
    }

    @Test(expected = RuntimeException.class)
    public void getAvailableCopyCaseEmpty() {
        this.bookInfoExample.getAvailableCopy();
    }

    @Test(expected = RuntimeException.class)
    public void getAvailableCopyCaseCopiesHaveLoans() {

        this.bookInfoExample.addCopy();
        this.bookInfoExample.addCopy();

        this.bookInfoExample.getAvailableCopy();
        this.bookInfoExample.getAvailableCopy();
        this.bookInfoExample.getAvailableCopy();
    }

    @Test
    public void getAvailableCopyWhenNormalUse() {

        this.bookInfoExample.addCopy();

        BookCopy availableCopy = this.bookInfoExample.getAvailableCopy();

        assertFalse("Must have an available copy", this.bookInfoExample.getAvailableCopies().contains(availableCopy));
        assertTrue("Must have an available copy", this.bookInfoExample.getRegistedCopies().contains(availableCopy));

    }

}
