package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Person;
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

    private Category drama;

    private Category aventura;

    private Category romance;

    private Category terror;

    private Author pablo;

    private Author guille;

    @Before
    public void setUp() {

        this.categories = new HashSet<Category>();

        this.drama = new Category("Drama");
        this.categories.add(this.drama);
        this.aventura = new Category("Aventura");
        this.categories.add(this.aventura);
        this.romance = new Category("Romance");
        this.categories.add(this.romance);
        this.terror = new Category("Terror");
        this.categories.add(this.terror);

        this.pablo = new Author("Pablo Funes");
        this.guille = new Author("Guille Mori");
        Publisher publisher1 = new Publisher("Editorial Amboro");
        Publisher publisher2 = new Publisher("Editorial Sarosi");

        this.aIsbn = new ISBN("12654665");
        this.book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(this.drama)
                .withCategory(this.aventura).withAuthor(this.pablo).withAuthor(this.guille).withPublisher(publisher1)
                .withCountOfCopies(1).withIsbn(this.aIsbn).build();

        this.book2 = new BookBuilder().withTitle("Muajaja").withCategory(this.romance).withCategory(this.terror)
                .withAuthor(this.pablo).withAuthor(this.guille).withPublisher(publisher2).withCountOfCopies(2).build();

        this.bookRepository.save(this.book);
        this.bookRepository.save(this.book2);
        this.bookRepository.getHibernateTemplate().flush();
        this.bookRepository.getHibernateTemplate().clear();

    }

    @Test
    public void count() {
        assertEquals("count must be 2", this.bookRepository.count(), 2);
    }

    @Test
    public void mappings() {
        Book aBook = this.bookRepository.findByEquality(this.book);

        assertEquals("Must be same title", aBook.getTitle(), this.book.getTitle());
        assertEquals("Must be same isbn", aBook.getIsbn().getValue(), this.book.getIsbn().getValue());
        assertEquals("Must be same publisher", aBook.getPublisher().getName(), this.book.getPublisher().getName());
        assertEquals("Must be same imagepath", aBook.getImagepath(), this.book.getImagepath());
        assertEquals("Must be same description", aBook.getDescription(), this.book.getDescription());
        assertEquals("Must be same authors", aBook.getAuthors().size(), this.book.getAuthors().size());
        assertEquals("Must be same countOfLouns", aBook.getCountOfLouns(), this.book.getCountOfLouns());
        assertEquals("Must be same reservationEvents", aBook.getReservationEvents().size(), this.book
                .getReservationEvents().size());
    }

    @Test
    public void findLikeTitle() {
        Book founded = this.bookRepository.findByPropertyLike("title", "Mago").get(0);
        assertTrue("Must containt Mago in the title", founded.getTitle().contains("Mago"));
    }

    @Test
    public void availableCopy() {
        Book aBook = this.bookRepository.findByEquality(this.book2);
        assertEquals("Must have 2 copies", 2, aBook.getAvailableCopies().size());
    }

    @Test
    public void getAvailableCopy() {
        Book aBook = this.bookRepository.findByEquality(this.book2);
        BookCopy copy = new LoanBuilder().withAgreedReturnDate(new DateTime().plus(10)).withPerson(new Person("pepe"))
                .withBookCopy(aBook.getAvailableCopy()).build().getBookCopy();
        this.bookRepository.save(aBook);
        this.bookRepository.getHibernateTemplate().flush();
        assertEquals("Must have 1 loan", 1, aBook.getCountOfLouns());
        assertEquals("Must have 1 available copy", (Integer) 1, aBook.getCountOfAvailableCopies());
        assertEquals("Must have 2 registered copies", 2, aBook.getRegistedCopies().size());
        assertTrue("Must containt this copy", aBook.getRegistedCopies().contains(copy));
        assertEquals("Must have this registered copy", copy, aBook.getRegistedCopies().get(0));
    }

    @Test
    public void categoriesHaveBook() {
        Category dramaDB = this.categoryRepository.findByEquality(this.drama);
        Category terrorDB = this.categoryRepository.findByEquality(this.terror);
        Category aventuraDB = this.categoryRepository.findByEquality(this.aventura);
        Category romanceDB = this.categoryRepository.findByEquality(this.romance);

        Iterator<Book> it = dramaDB.getBooks().iterator();
        assertEquals("Drama must contain book", it.next().getId(), this.book.getId());
        it = aventuraDB.getBooks().iterator();
        assertEquals("Aventura must contain book", it.next().getId(), this.book.getId());
        it = romanceDB.getBooks().iterator();
        assertEquals("Romance must contain book2", it.next().getId(), this.book2.getId());
        it = terrorDB.getBooks().iterator();
        assertEquals("Terror must contain book", it.next().getId(), this.book2.getId());
    }

    @Test
    public void authorsHaveBook() {
        Author pabloDB = this.authorRepository.findByEquality(this.pablo);
        Author guilleDB = this.authorRepository.findByEquality(this.guille);

        assertEquals("Pablo should have 2 books", 2, pabloDB.getBooks().size());
        Iterator<Book> it = pabloDB.getBooks().iterator();
        int pabloBookID = it.next().getId();
        int pabloBook2ID = it.next().getId();
        assertTrue("Pablo must contain book", pabloBookID == this.book.getId() || pabloBookID == this.book2.getId());
        assertTrue("Pablo must contain book2", pabloBook2ID == this.book.getId() || pabloBook2ID == this.book2.getId());

        Iterator<Book> it2 = guilleDB.getBooks().iterator();
        int guilleBookID = it2.next().getId();
        int guilleBook2ID = it2.next().getId();
        assertTrue("Pablo must contain book", guilleBookID == this.book.getId() || guilleBookID == this.book2.getId());
        assertTrue("Pablo must contain book2",
                guilleBook2ID == this.book.getId() || guilleBook2ID == this.book2.getId());
    }

    @Test
    public void secondAuthor() {
        assertEquals("Must be this author", this.guille, this.book.getAuthors().get(1));
    }

    @Test
    public void reservationEvent() {
        Book aBook = this.bookRepository.findByEquality(this.book2);

        ReservationEvent reservation = new ReservationEvent();
        aBook.addReservationEvent(reservation);

        this.bookRepository.save(aBook);
        this.bookRepository.getHibernateTemplate().flush();

        Book aBook2 = this.bookRepository.findByEquality(this.book2);

        assertEquals("Must contain a reservation", 1, aBook2.getReservationEvents().size());
        assertEquals("Must be this reservation", reservation, aBook2.getReservationEvents().get(0));

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

    public Author getAuthor2() {
        return this.guille;
    }

    public void setAuthor2(final Author author2) {
        this.guille = author2;
    }

}
