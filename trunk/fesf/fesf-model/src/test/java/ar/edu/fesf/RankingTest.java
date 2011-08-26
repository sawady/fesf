package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.fesf.model.BookInfo;
import ar.edu.fesf.model.Ranking;

public class RankingTest {

    private BookInfo bookMock;

    private Ranking rank;

    @Before
    public void setUp() {
        this.bookMock = mock(BookInfo.class);
        this.rank = new Ranking();
    }

    @Test
    public void addToRecentsWhenEmpty() {
        this.rank.addToRecents(this.bookMock);
        assertTrue("Recent addition is not contained when empty",
                this.rank.getRecentlyAvailable().contains(this.bookMock));
        assertEquals("Unexpected list size when empty", 1, this.rank.getRecentlyAvailable().size());

    }

    @Test
    public void addToRecentsWhenFull() {
        BookInfo firstBook = new BookInfo();
        this.rank.addToRecents(firstBook);
        for (int i = this.rank.getLimitOfRecentlyBooks() - 1; i > 0; i = i - 1) {
            this.rank.addToRecents(mock(BookInfo.class));
        }
        this.rank.addToRecents(this.bookMock);
        assertTrue("Recent addition is not contained when full",
                this.rank.getRecentlyAvailable().contains(this.bookMock));
        assertFalse("Oldest addition is contained after addition", this.rank.getRecentlyAvailable().contains(firstBook));
        assertEquals("Unexpected list size when full", this.rank.getLimitOfRecentlyBooks(), this.rank
                .getRecentlyAvailable().size());

    }

    @Test
    public void updateRankingWhenEmpty() {
        assertEquals("Unexpected list size", 0, this.rank.getTop20().size());
        BookInfo bookMock1 = mock(BookInfo.class);
        BookInfo bookMock2 = mock(BookInfo.class);
        BookInfo bookMock3 = mock(BookInfo.class);
        BookInfo bookMock4 = mock(BookInfo.class);
        when(bookMock1.getCountOfLouns()).thenReturn(5);
        when(bookMock2.getCountOfLouns()).thenReturn(1);
        when(bookMock3.getCountOfLouns()).thenReturn(3);
        when(bookMock4.getCountOfLouns()).thenReturn(8);
        this.rank.updateRanking(bookMock1);
        this.rank.updateRanking(bookMock2);
        this.rank.updateRanking(bookMock3);
        this.rank.updateRanking(bookMock4);
        assertEquals("Unexpected index when updating0", 0, this.rank.getTop20().indexOf(bookMock4));// 8
        assertEquals("Unexpected index when updating", 1, this.rank.getTop20().indexOf(bookMock1));// 5
        assertEquals("Unexpected index when updating", 2, this.rank.getTop20().indexOf(bookMock3));// 3
        assertEquals("Unexpected index when updating", 3, this.rank.getTop20().indexOf(bookMock2));// 1
        assertEquals("Unexpected list size", 4, this.rank.getTop20().size());
    }

    @Test
    public void updateRankingWhenFull() {
        BookInfo book;
        for (int i = 20; i > 0; i = i - 1) {
            book = mock(BookInfo.class);
            when(book.getCountOfLouns()).thenReturn(i);
            this.rank.getTop20().add(book);
        }
        when(this.bookMock.getCountOfLouns()).thenReturn(5);
        assertEquals("Unexpected list size", 20, this.rank.getTop20().size());
        this.rank.updateRanking(this.bookMock);
        assertTrue("Recent addition is not contained", this.rank.getTop20().contains(this.bookMock));
        assertEquals("Unexpected index", 16, this.rank.getTop20().indexOf(this.bookMock));
        assertNotSame("Unexpected index", this.rank.getTop20().indexOf(this.bookMock) == 15);
        assertNotSame("Unexpected index", this.rank.getTop20().indexOf(this.bookMock) == 17);
        assertEquals("Unexpected list size", 20, this.rank.getTop20().size());
    }
}
