package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.builders.LoanBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.ReservationEvent;
import ar.edu.fesf.repositories.AuthorRepository;
import ar.edu.fesf.repositories.BookRepository;
import ar.edu.fesf.repositories.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Set<Category> categories;

    private Book book;

    private Book book2;

    private ISBN aIsbn;

    @Before
    public void setUp() {

        this.categories = new HashSet<Category>();

        Category drama = new Category("Drama");
        this.categories.add(drama);
        Category aventura = new Category("Aventura");
        this.categories.add(aventura);
        Category romance = new Category("Romance");
        this.categories.add(romance);
        Category terror = new Category("Terror");
        this.categories.add(terror);

        Author author1 = new Author();
        author1.setName("Pablo Funes");
        Author author2 = new Author();
        author2.setName("Guille Mori");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editorial Amboro");
        Publisher publisher2 = new Publisher();
        publisher1.setName("Editorial Sarosi");

        this.aIsbn = new ISBN("12654665");
        this.book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(1)
                .withIsbn(this.aIsbn).build();

        this.book2 = new BookBuilder().withTitle("Muajaja").withCategory(romance).withCategory(terror)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher2).withCountOfCopies(2).build();

        this.bookRepository.save(this.book);
        this.bookRepository.save(this.book2);

    }

    @Test
    @Rollback(false)
    public void count() {
        assertEquals("count must be 2", this.bookRepository.count(), 2);
    }

    @Test
    public void findById() {
        assertEquals("Must be same book", this.bookRepository.findById(this.book.getId()), this.book);
    }

    @Test
    public void mappings() {
        Book aBook = this.bookRepository.findByEquality(this.book);

        assertEquals("Must be same title", this.bookRepository.findByPropertyUnique("title", this.book.getTitle())
                .getTitle(), this.book.getTitle());

        assertEquals("Must be same book", aBook, this.book);
    }

    @Test
    public void availableCopy() {
        Book aBook = this.bookRepository.findByEquality(this.book2);
        assertEquals("Must have 2 copies", 2, aBook.getAvailableCopies().size());
    }

    @Test
    public void getAvailableCopy() {
        Book aBook = this.bookRepository.findByEquality(this.book2);
        BookCopy copy = aBook.getAvailableCopy();
        copy.addLoan(new LoanBuilder().withAgreedReturnDate(new DateTime().plus(10)).build());
        this.bookRepository.save(aBook);
        this.bookRepository.getHibernateTemplate().flush();
        Book aBook2 = this.bookRepository.findByEquality(this.book2);
        assertEquals("Must have 1 loan", 1, aBook2.getCountOfLouns());
        assertEquals("Must have 1 available copy", 1, aBook2.getCountOfCopies());
        assertEquals("Must have 2 registered copies", 2, aBook2.getRegistedCopies().size());
        assertTrue("Must containt this copy", aBook2.getRegistedCopies().contains(copy));
    }

    @Test
    public void categoriesHaveBook() {
        for (Category category : this.categoryRepository.findAll()) {
            assertTrue("must containt either book", category.contains(this.book) || category.contains(this.book2));
        }

    }

    @Test
    public void authorsHaveBook() {
        for (Author author : this.authorRepository.findAll()) {
            assertTrue("must contain this books", author.contains(this.book));
            assertTrue("must contain this books", author.contains(this.book2));
        }
    }

    @Test
    public void reservationEvent() {
        Book aBook = this.bookRepository.findByEquality(this.book2);

        aBook.addReservationEvent(new ReservationEvent());

        this.bookRepository.save(aBook);
        this.bookRepository.getHibernateTemplate().flush();

        Book aBook2 = this.bookRepository.findByEquality(this.book2);

        assertEquals("Must contain a reservation", 1, aBook2.getReservationEvents().size());

    }

    public BookRepository getBookRepository() {
        return this.bookRepository;
    }

    public void setBookRepository(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }

    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public AuthorRepository getAuthorRepository() {
        return this.authorRepository;
    }

    public void setAuthorRepository(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Book getBook2() {
        return this.book2;
    }

    public void setBook2(final Book book2) {
        this.book2 = book2;
    }

    public ISBN getaIsbn() {
        return this.aIsbn;
    }

    public void setaIsbn(final ISBN isbn) {
        this.aIsbn = isbn;
    }

}
