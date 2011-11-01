package ar.edu.fesf.services;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.builders.LoanBuilder;
import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.EmailAddress;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.CategoryRepository;

public class SpringInitializedService {

    private CategoryRepository categoryRepository;

    private BookService bookService;

    private PersonService personService;

    private LoaningService loaningService;

    @Transactional
    public void initialize() {

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

        Book magoDeTerramar = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama)
                .withCategory(aventura).withAuthor(author1).withAuthor(author2).withPublisher(publisher1)
                .withCountOfCopies(5).withIsbn(new ISBN("978-0307957122")).build();
        this.bookService.registerNewBook(magoDeTerramar);

        this.bookService.registerNewBook(new BookBuilder().withTitle("Los Crimenes de la Calle Morgue")
                .withCategory(drama).withIsbn(new ISBN("978-0307957123")).withCategory(policial)
                .withPublisher(publisher1).withCountOfCopies(1).build());

        this.bookService.registerNewBook(new BookBuilder().withTitle("Maleficio").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957124")).withCountOfCopies(10).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Cementerio de Animales").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957125")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Carrie").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957126")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Ojos de Fuego").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957127")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("I.T. El Payaso Maldito")
                .withPublisher(publisher1).withCategory(terror).withIsbn(new ISBN("978-0307957128"))
                .withCountOfCopies(5).build());

        this.bookService.registerNewBook(new BookBuilder().withTitle("El camino hacia el dorado")
                .withCategory(aventura).withIsbn(new ISBN("978-0307957129")).withPublisher(publisher2)
                .withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Tosti").withCategory(aventura)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957130")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Mickey y las Abichuelas").withCategory(aventura)
                .withIsbn(new ISBN("978-0307957131")).withPublisher(publisher2).withCountOfCopies(5).build());

        this.bookService.registerNewBook(new BookBuilder().withTitle("Mi Planta de Naranja Lima").withCategory(drama)
                .withIsbn(new ISBN("978-0307957132")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("El Genio en la botella").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957133")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Mi Nombre es Sam").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957134")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Naufrago").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957135")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Full Metal Jacket").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957136")).withCountOfCopies(5).build());

        this.bookService.registerNewBook(new BookBuilder().withTitle("El Manifiesto Comunista").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957137")).withPublisher(publisher2).withCountOfCopies(10).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Principia Mathematica").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957138")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Platon y sus amigos").withCategory(filosofia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957139")).withCountOfCopies(5).build());

        this.bookService.registerNewBook(new BookBuilder().withTitle("Locademia de Policias").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957140")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("La pistola desnuda").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957141")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Esa maldita costilla").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957142")).withCountOfCopies(5).build());
        this.bookService.registerNewBook(new BookBuilder().withTitle("Las locuras del emperador").withCategory(comedia)
                .withCountOfCopies(5).withIsbn(new ISBN("978-0307957143")).withPublisher(publisher2).build());

        Person jose = new PersonBuilder().withName("Jose").withEmail(new EmailAddress("jose@gmail.com"))
                .withUserInfo(new UserInfo("jose", "jose", Role.LIBRARIAN)).build();
        this.personService.save(jose);
        this.personService.save(new PersonBuilder().withName("Federico").withSurname("Sawady")
                .withEmail(new EmailAddress("sawady.faso@gmail.com"))
                .withUserInfo(new UserInfo("fede", "fede", Role.LIBRARIAN)).build());
        this.personService.save(new PersonBuilder().withName("Pepe").withEmail(new EmailAddress("pepe@gmail.com"))
                .withUserInfo(new UserInfo("pepe", "pepe", Role.LIBRARIAN)).build());
        this.personService.save(new PersonBuilder().withName("Carlos").withEmail(new EmailAddress("carlos@gmail.com"))
                .withUserInfo(new UserInfo("carlos", "carlos", Role.USER)).build());
        this.personService.save(new PersonBuilder().withName("Tomas").withEmail(new EmailAddress("tomas@gmail.com"))
                .withUserInfo(new UserInfo("tomas", "tomas", Role.USER)).build());
        this.personService.save(new PersonBuilder().withName("matias").withEmail(new EmailAddress("matias@gmail.com"))
                .withUserInfo(new UserInfo("matias", "matias", Role.USER)).build());

        this.loaningService.registerLoan(jose, new LoanBuilder().withAgreedReturnDate(new DateTime().plusDays(20))
                .withMaxLoanPeriodInDays(60).build(), magoDeTerramar);

    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public LoaningService getLoaningService() {
        return this.loaningService;
    }

    public void setLoaningService(final LoaningService loaningService) {
        this.loaningService = loaningService;
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }

    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
