package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Comment;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.UserFeedbackManager;
import ar.edu.fesf.repositories.BookRepository;
import ar.edu.fesf.repositories.CategoryRepository;
import ar.edu.fesf.services.dtos.CommentDTO;
import ar.edu.fesf.services.dtos.EditBookDTO;
import ar.edu.fesf.services.dtos.NewBookDTO;

public class BookService extends GenericTransactionalRepositoryService<Book> {

    private static final long serialVersionUID = 7521127091837519541L;

    private PublisherService publisherService;

    private CategoryRepository categoryRepository;

    public List<String> getFieldForSort() {
        List<String> names = new ArrayList<String>();
        names.add("title");
        return names;
    }

    public List<String> getFieldNames() {
        List<String> names = new ArrayList<String>();
        names.add("title");
        return names;
    }

    @Transactional(readOnly = true)
    public List<Book> bookSearch(final String input) {
        List<Book> results = this.getBookRepository().bookSearch(input);
        this.initialize(results, List.class);
        return results;
    }

    @Transactional
    public void registerNewBook(final Book book) {
        this.save(book);
    }

    @Transactional
    public void registerNewBookDTO(final NewBookDTO bookDTO) {
        this.registerNewBook(new BookBuilder().withTitle(bookDTO.getTitle()).withIsbn(new ISBN(bookDTO.getIsbn()))
                .withPublisher(new Publisher(bookDTO.getPublisher())).withDescription(bookDTO.getDescription())
                .withCountOfCopies(bookDTO.getCountOfCopies()).build());
    }

    @Transactional
    public void registerEditBookDTO(final EditBookDTO bookDTO) {

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

        this.save(bookDB);

    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return this.getCategoryRepository().findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategory(final Category category) {
        // TODO hacer que la consulta sea por DB y que venga ordenada por los mejores libros
        // de esa categoria
        List<Book> books = new ArrayList<Book>();
        for (Category booksEx : this.getCategoryRepository().findByExample(category)) {
            books.addAll(booksEx.getBooks());
        }
        return books;
    }

    @Transactional
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
    public CommentDTO initializeComment(final Comment comment) {
        this.getRepository().initialize(comment.getCalification());
        this.getRepository().initialize(comment.getPerson());
        return new CommentDTO(comment.getBody(), comment.getCalification().getValue(), comment.getPerson().getName());
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

    private BookRepository getBookRepository() {
        return (BookRepository) this.getRepository();
    }

    public void registerComment(final CommentDTO commentDTO, final Book book, final Person person) {
        Book bookDB = this.findByEquality(book);
        Comment newComment = new Comment(commentDTO.getBody(), commentDTO.getCalification(), bookDB, person);
        bookDB.addComment(newComment);
        this.save(bookDB);
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
