package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.Ranking;
import ar.edu.fesf.repositories.BookRepository;
import ar.edu.fesf.repositories.RankingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class RankingRepositoryTest {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    private Book book2;

    private Ranking ranking;

    @Before
    public void setUp() {
        Set<Category> categories = new HashSet<Category>();

        Category drama = new Category("Drama");
        categories.add(drama);

        Author author1 = new Author("Pablo Funes");
        Author author2 = new Author("Guille Mori");
        Publisher publisher1 = new Publisher("Editorial Amboro");
        Publisher publisher2 = new Publisher("Editorial Sarosi");

        ISBN aIsbn = new ISBN("12654665");
        this.book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama).withAuthor(author1)
                .withAuthor(author2).withPublisher(publisher1).withCountOfCopies(1).withIsbn(aIsbn).build();

        this.book2 = new BookBuilder().withTitle("Muajaja").withCategory(drama).withAuthor(author1).withAuthor(author2)
                .withPublisher(publisher2).withCountOfCopies(2).build();

        this.ranking = new Ranking();

        this.ranking.addToRecents(this.book);
        this.ranking.addToRecents(this.book2);
        this.ranking.updateRanking(this.book);
        this.ranking.updateRanking(this.book2);

        this.rankingRepository.save(this.ranking);
        this.rankingRepository.getHibernateTemplate().flush();
        this.rankingRepository.getHibernateTemplate().clear();
    }

    @Test
    public void mappings() {
        Ranking aRanking = this.rankingRepository.findByEquality(this.ranking);
        Book bookBD = this.bookRepository.findByEquality(this.book);
        Book bookBD2 = this.bookRepository.findByEquality(this.book2);
        assertEquals("Top20 must be 2 items long", aRanking.getTop20().size(), 2);
        assertEquals("RecentlyAvailable must be 2 items long", aRanking.getRecentlyAvailable().size(), 2);
        assertTrue("Top20 should contain book that is not there.", aRanking.getTop20().contains(bookBD));
        assertTrue("Book must be the first in the Top20", aRanking.getTop20().get(0).equals(bookBD));
        assertFalse("Book2 must not be the first in the Top20", aRanking.getTop20().get(0).equals(bookBD2));
        assertTrue("Top20 should contain book2 that is not there.", aRanking.getTop20().contains(bookBD2));
        assertTrue("RecentlyAvailable should contain a book that is not there.", aRanking.getRecentlyAvailable()
                .contains(bookBD));
        assertTrue("RecentlyAvailable should contain a book that is not there.", aRanking.getRecentlyAvailable()
                .contains(bookBD2));
    }

    /* ACCESSORS */

    public void setRankingRepository(final RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public RankingRepository getRankingRepository() {
        return this.rankingRepository;
    }

    public void setBookRepository(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return this.bookRepository;
    }

}
