package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.BookCopy;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.Ranking;
import ar.edu.fesf.repositories.CategoryRepository;
import ar.edu.fesf.repositories.RankingRepository;

public class BookService extends GenericTransactionalRepositoryService<Book> {

    private static final long serialVersionUID = 7521127091837519541L;

    private CategoryRepository categoryRepository;

    private RankingRepository rankingRepository;

    private Ranking ranking;

    /**
     * TODO si lo dejan acá, saquen está construcción en otro objeto así no que queda sucio el service
     */
    public void initialize() {
        Ranking aRanking = new Ranking();
        this.setRanking(aRanking);
        this.getRankingRepository().save(aRanking);

        Category drama = new Category("Drama");
        this.getCategoryRepository().save(drama);
        Category terror = new Category("Terror");
        this.getCategoryRepository().save(terror);
        Category policial = new Category("Policial");
        this.getCategoryRepository().save(policial);
        Category aventura = new Category("Aventura");
        this.getCategoryRepository().save(aventura);
        Category comedia = new Category("Comedia");
        this.getCategoryRepository().save(comedia);
        Category filosofia = new Category("Filosofia");
        this.getCategoryRepository().save(filosofia);

        Author author1 = new Author();
        author1.setName("Pablo Funes");
        Author author2 = new Author();
        author2.setName("Guille Mori");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editorial Amboro");
        Publisher publisher2 = new Publisher();
        publisher2.setName("Editorial Canete");

        this.save(new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(5)
                .withIsbn(new ISBN("978-0307957122")).build());

        this.save(new BookBuilder().withTitle("Los Crimenes de la Calle Morgue").withCategory(drama)
                .withIsbn(new ISBN("978-0307957123")).withCategory(policial).withPublisher(publisher1)
                .withCountOfCopies(1).build());

        this.save(new BookBuilder().withTitle("Maleficio").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957124")).withCountOfCopies(10).build());
        this.save(new BookBuilder().withTitle("Cementerio de Animales").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957125")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Carrie").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957126")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Ojos de Fuego").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957127")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("I.T. El Payaso Maldito").withPublisher(publisher1).withCategory(terror)
                .withIsbn(new ISBN("978-0307957128")).withCountOfCopies(5).build());

        this.save(new BookBuilder().withTitle("El camino hacia el dorado").withCategory(aventura)
                .withIsbn(new ISBN("978-0307957129")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Tosti").withCategory(aventura).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957130")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Mickey y las Abichuelas").withCategory(aventura)
                .withIsbn(new ISBN("978-0307957131")).withPublisher(publisher2).withCountOfCopies(5).build());

        this.save(new BookBuilder().withTitle("Mi Planta de Naranja Lima").withCategory(drama)
                .withIsbn(new ISBN("978-0307957132")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("El Genio en la botella").withCategory(drama).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957133")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Mi Nombre es Sam").withCategory(drama).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957134")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Naufrago").withCategory(drama).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957135")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Full Metal Jacket").withCategory(drama).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957136")).withCountOfCopies(5).build());

        this.save(new BookBuilder().withTitle("El Manifiesto Comunista").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957137")).withPublisher(publisher2).withCountOfCopies(10).build());
        this.save(new BookBuilder().withTitle("Principia Mathematica").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957138")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Platon y sus amigos").withCategory(filosofia).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957139")).withCountOfCopies(5).build());

        this.save(new BookBuilder().withTitle("Locademia de Policias").withCategory(comedia).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957140")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("La pistola desnuda").withCategory(comedia).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957141")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Esa maldita costilla").withCategory(comedia).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957142")).withCountOfCopies(5).build());
        this.save(new BookBuilder().withTitle("Las locuras del emperador").withCategory(comedia).withCountOfCopies(5)
                .withIsbn(new ISBN("978-0307957143")).withPublisher(publisher2).build());

    }

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

    @Override
    @Transactional
    public void save(final Book entity) {
        super.save(entity);
        this.getRanking().addToRecents(entity);
        this.getRanking().updateRanking(entity);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return this.getCategoryRepository().findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategory(final Category category) {
        List<Book> books = new ArrayList<Book>();
        for (Category booksEx : this.getCategoryRepository().findByExample(category)) {
            books.addAll(booksEx.getBooks());
        }
        return books;
    }

    @Transactional(readOnly = true)
    public List<Book> getTop20() {
        return this.getRanking().getTop20();
    }

    @Transactional(readOnly = true)
    public List<Book> getRecentlyAvailable() {
        return this.getRanking().getRecentlyAvailable();
    }

    @Transactional(readOnly = true)
    public boolean hasAvailableCopy(final Book book) {
        return this.findById(book.getId()).hasAvailableCopy();
    }

    @Transactional
    public BookCopy getAvailableCopy(final Book book) {
        Book newBook = this.findById(book.getId());
        BookCopy bookCopy = newBook.getAvailableCopy();
        this.save(newBook);
        return bookCopy;
    }

    @Transactional(readOnly = true)
    public Integer getCountOfAvailableCopies(final Book book) {
        return this.findById(book.getId()).getCountOfAvailableCopies();
    }

    @Transactional(readOnly = true)
    public Integer getCountOfRegisteredCopies(final Book book) {
        return this.findById(book.getId()).getCountOfRegisterdCopies();
    }

    @Transactional(readOnly = true)
    public List<Author> getAuthors(final Book book) {
        List<Author> authors = new ArrayList<Author>();
        authors.addAll(this.findByEquality(book).getAuthors());
        return authors;
    }

    @Transactional(readOnly = true)
    public String getPublisherName(final Book book) {
        return this.findByEquality(book).getPublisher().getName();
    }

    @Transactional(readOnly = true)
    public Publisher getPublisher(final Book book) {
        return this.findByEquality(book).getPublisher();
    }

    @Transactional(readOnly = true)
    public Set<Category> getCategories(final Book book) {
        Set<Category> categories = new HashSet<Category>();
        categories.addAll(this.findByEquality(book).getCategories());
        return categories;
    }

    @Transactional(readOnly = true)
    public ISBN getISBN(final Book book) {
        return this.findByEquality(book).getIsbn();
    }

    /* Accessors */

    public void setRankingRepository(final RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public RankingRepository getRankingRepository() {
        return this.rankingRepository;
    }

    public void setRanking(final Ranking ranking) {
        this.ranking = ranking;
    }

    public Ranking getRanking() {
        return this.ranking;
    }

    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }

}
