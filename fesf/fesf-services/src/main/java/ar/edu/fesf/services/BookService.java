package ar.edu.fesf.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.dtos.CommentDTO;
import ar.edu.fesf.dtos.EditBookDTO;
import ar.edu.fesf.dtos.NewBookDTO;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Comment;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.ReservationEvent;
import ar.edu.fesf.model.UserFeedbackManager;
import ar.edu.fesf.others.GenericTransactionalRepositoryService;
import ar.edu.fesf.repositories.BookRepository;
import ar.edu.fesf.repositories.CategoryRepository;

@Service
public class BookService extends GenericTransactionalRepositoryService<Book> {

    private static final long serialVersionUID = 7521127091837519541L;

    private PublisherService publisherService;

    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Book> bookSearch(final String input) {
        List<Book> results = this.getBookRepository().bookSearch(input, true);
        this.initialize(results, List.class);
        return results;
    }

    @Transactional(readOnly = true)
    @Secured(value = { "ROLE_LIBRARIAN" })
    public List<Book> bookSearchForLibrarian(final String input) {
        List<Book> results = this.getBookRepository().bookSearch(input, false);
        this.initialize(results, List.class);
        return results;
    }

    @Transactional
    @Secured(value = { "ROLE_LIBRARIAN" })
    public Book registerNewBookDTO(final NewBookDTO bookDTO) {
        Book newBook = new BookBuilder().withTitle(bookDTO.getTitle()).withIsbn(new ISBN(bookDTO.getIsbn()))
                .withPublisher(new Publisher(bookDTO.getPublisher())).withDescription(bookDTO.getDescription())
                .withCountOfCopies(bookDTO.getCountOfCopies()).withAvailable(bookDTO.getAvailable()).build();
        this.save(newBook);
        return newBook;
    }

    @Transactional
    @Secured(value = { "ROLE_LIBRARIAN" })
    public Book registerEditBookDTO(final EditBookDTO bookDTO) {

        Book bookDB = this.findById(bookDTO.getId());

        bookDB.setTitle(bookDTO.getTitle());
        bookDB.setIsbn(new ISBN(bookDTO.getIsbn()));
        bookDB.setDescription(bookDTO.getDescription());

        List<Publisher> publishersMatching = this.getPublisherService().findByProperty("name", bookDTO.getPublisher());

        Publisher publisher;
        if (publishersMatching.isEmpty()) {
            publisher = new Publisher(bookDTO.getPublisher());
        } else {
            publisher = publishersMatching.get(0);
        }

        bookDB.setPublisher(publisher);
        bookDB.setAvailable(bookDTO.getAvailable());

        this.save(bookDB);

        return bookDB;

    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return this.getCategoryRepository().findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategory(final Category category) {
        List<Book> results = this.getBookRepository().findByCategory(category);
        this.initialize(results, List.class);
        return results;
    }

    @Transactional
    @Secured(value = { "ROLE_USER" })
    public BookCopy getAvailableCopy(final Book book) {
        Book newBook = this.findByEquality(book);
        BookCopy bookCopy = newBook.getAvailableCopy();
        this.save(newBook);
        return bookCopy;
    }

    @Transactional(readOnly = true)
    public UserFeedbackManager getUserFeedback(final Book book) {
        return this.initializeFields(book, "userFeedbackManager").getUserFeedbackManager();
    }

    @Transactional(readOnly = true)
    public Book initializeBookInfo(final Book book) {
        return this.initializeFields(book, "publisher", "isbn", "authors", "categories", "availableCopies",
                "registedCopies");
    }

    @Transactional(readOnly = true)
    public List<Book> getTop20() {
        List<Book> top20 = this.getBookRepository().getTop20();
        this.initialize(top20, List.class);
        return top20;
    }

    @Transactional(readOnly = true)
    public List<Book> getRecentlyAvailable() {
        List<Book> recentlyAvailables = this.getBookRepository().getRecentlyAvailable(20);
        this.initialize(recentlyAvailables, List.class);
        return recentlyAvailables;
    }

    @Transactional(readOnly = true)
    public List<Book> relatedBooks(final int bookID, final int maxResults) {
        List<Book> booksDB = this.getBookRepository().booksBorrowedByThoseWhoBorrowed(bookID, maxResults);
        this.initialize(booksDB, List.class);
        return booksDB;
    }

    @Transactional
    @Secured(value = { "ROLE_USER" })
    public void registerComment(final CommentDTO commentDTO, final Book book, final Person person) {
        Book bookDB = this.findByEquality(book);
        Comment newComment = new Comment(commentDTO.getBody(), commentDTO.getCalification(), bookDB, person);
        bookDB.addComment(newComment);
        this.save(bookDB);
    }

    @Transactional
    @Secured(value = { "ROLE_USER" })
    public void addReservationEvent(final Book book, final Person person) {
        Book bookDB = this.findByEquality(book);
        bookDB.addReservationEvent(new ReservationEvent(person, bookDB));
    }

    @Transactional
    @Secured(value = { "ROLE_USER" })
    public boolean isInTheReservationList(final Book book, final Person person) {
        Book bookDB = this.findByEquality(book);
        return bookDB.isInTheReservationList(person);
    }

    private BookRepository getBookRepository() {
        return (BookRepository) this.getRepository();
    }

    /* Accessors */

    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }

    public void setPublisherService(final PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public PublisherService getPublisherService() {
        return this.publisherService;
    }

}
