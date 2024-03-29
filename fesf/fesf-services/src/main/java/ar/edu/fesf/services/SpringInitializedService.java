package ar.edu.fesf.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.BookBuilder;
import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.dtos.BorrowItDTO;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Comment;
import ar.edu.fesf.model.EmailAddress;
import ar.edu.fesf.model.ISBN;
import ar.edu.fesf.model.Location;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.repositories.CategoryRepository;
import ar.edu.fesf.repositories.LocationRepository;

@Service
public class SpringInitializedService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    @Autowired
    private LoaningService loaningService;

    @Autowired
    private GoogleBookService googleBookService;

    @Transactional
    public void initialize() {

        Category drama = new Category("Drama");
        // this.getCategoryRepository().save(drama);
        Category terror = new Category("Terror");
        // this.getCategoryRepository().save(terror);
        Category policial = new Category("Policial");
        // this.getCategoryRepository().save(policial);
        Category aventura = new Category("Aventura");
        // this.getCategoryRepository().save(aventura);
        Category comedia = new Category("Comedia");
        // this.getCategoryRepository().save(comedia);
        Category filosofia = new Category("Filosofia");
        // this.getCategoryRepository().save(filosofia);

        Author author1 = new Author();
        author1.setName("Pablo Funes");
        Author author2 = new Author();
        author2.setName("Guille Mori");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editorial Amboro");
        Publisher publisher2 = new Publisher();
        publisher2.setName("Editorial Canete");
        
        this.locationRepository.save(new Location("Buenos Aires"));
        this.locationRepository.save(new Location("Parana"));
        this.locationRepository.save(new Location("Bahia Blanca"));
        this.locationRepository.save(new Location("La Plata"));

        Book magoDeTerramar = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama)
                .withCategory(aventura).withAuthor(author1).withAuthor(author2).withPublisher(publisher1)
                .withCountOfCopies(2).withIsbn(new ISBN("978-0307957122")).build();
        this.bookService.save(magoDeTerramar);

        Book mujercitas = new BookBuilder().withTitle("Mujercitas").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(2)
                .withIsbn(new ISBN("978-0307977122")).build();
        this.bookService.save(mujercitas);

        Book justinBieber = new BookBuilder().withTitle("Justin Bieber Fan").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(2)
                .withIsbn(new ISBN("978-0307977122")).build();
        this.bookService.save(justinBieber);

        this.bookService.save(new BookBuilder().withTitle("Los Crimenes de la Calle Morgue").withCategory(drama)
                .withIsbn(new ISBN("978-0307957123")).withCategory(policial).withPublisher(publisher1)
                .withCountOfCopies(1).build());

        Book maleficio = new BookBuilder().withTitle("Maleficio").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957124")).withCountOfCopies(10).build();
        this.bookService.save(maleficio);
        this.bookService.save(new BookBuilder().withTitle("Cementerio de Animales").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957125")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Carrie").withCategory(terror).withPublisher(publisher1)
                .withIsbn(new ISBN("978-0307957126")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Ojos de Fuego").withCategory(terror)
                .withPublisher(publisher1).withIsbn(new ISBN("978-0307957127")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("I.T. El Payaso Maldito").withPublisher(publisher1)
                .withCategory(terror).withIsbn(new ISBN("978-0307957128")).withCountOfCopies(5).build());

        this.bookService.save(new BookBuilder().withTitle("El camino hacia el dorado").withCategory(aventura)
                .withIsbn(new ISBN("978-0307957129")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Tosti").withCategory(aventura).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957130")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Mickey y las Abichuelas").withCategory(aventura)
                .withIsbn(new ISBN("978-0307957131")).withPublisher(publisher2).withCountOfCopies(5).build());

        this.bookService.save(new BookBuilder().withTitle("Mi Planta de Naranja Lima").withCategory(drama)
                .withIsbn(new ISBN("978-0307957132")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("El Genio en la botella").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957133")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Mi Nombre es Sam").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957134")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Naufrago").withCategory(drama).withPublisher(publisher2)
                .withIsbn(new ISBN("978-0307957135")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Full Metal Jacket").withCategory(drama)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957136")).withCountOfCopies(5).build());

        this.bookService.save(new BookBuilder().withTitle("El Manifiesto Comunista").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957137")).withPublisher(publisher2).withCountOfCopies(10).build());
        this.bookService.save(new BookBuilder().withTitle("Principia Mathematica").withCategory(filosofia)
                .withIsbn(new ISBN("978-0307957138")).withPublisher(publisher2).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Platon y sus amigos").withCategory(filosofia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957139")).withCountOfCopies(5).build());

        this.bookService.save(new BookBuilder().withTitle("Locademia de Policias").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957140")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("La pistola desnuda").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957141")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Esa maldita costilla").withCategory(comedia)
                .withPublisher(publisher2).withIsbn(new ISBN("978-0307957142")).withCountOfCopies(5).build());
        this.bookService.save(new BookBuilder().withTitle("Las locuras del emperador").withCategory(comedia)
                .withCountOfCopies(5).withIsbn(new ISBN("978-0307957143")).withPublisher(publisher2).build());

        // PRUEBA?

        // NewBookDTO bookDTO = this.googleBookService.search("978-1451648539").getItems().get(0);
        // this.bookService.registerNewBookDTO(bookDTO);

        Person cristian = new PersonBuilder().withName("Cristian").withSurname("Lopez").withAge(32)
                .withAddress("Devapp").withPhone("46546456").withEmail(new EmailAddress("cristianelopez@gmail.com"))
                .withRole(Role.ROLE_LIBRARIAN).build();

        Person fede = new PersonBuilder().withName("Federico").withSurname("Sawady").withAge(21)
                .withAddress("Colon 355").withPhone("42245630").withEmail(new EmailAddress("sawady.faso@gmail.com"))
                .withRole(Role.ROLE_LIBRARIAN).build();
        Person elias = new PersonBuilder().withName("Elias").withSurname("Filipponi").withAge(30)
                .withAddress("Ni idea").withPhone("43224568").withEmail(new EmailAddress("eliasfilipponi@gmail.com"))
                .withRole(Role.ROLE_LIBRARIAN).build();

        this.personService.save(fede);
        this.personService.save(elias);
        this.personService.save(cristian);

        this.loaningService.registerLoan(fede, new BorrowItDTO(new DateTime().plusDays(20).toString()), magoDeTerramar);
        this.loaningService.registerLoan(fede, new BorrowItDTO(new DateTime().minusDays(20).toString()), maleficio);
        this.loaningService
                .registerLoan(elias, new BorrowItDTO(new DateTime().plusDays(20).toString()), magoDeTerramar);

        this.loaningService
                .registerLoan(cristian, new BorrowItDTO(new DateTime().minusDays(20).toString()), mujercitas);
        this.loaningService.registerLoan(cristian, new BorrowItDTO(new DateTime().minusDays(20).toString()),
                justinBieber);

        // Book magoDeTerramarDB = this.bookService.findByEquality(magoDeTerramar);
        magoDeTerramar.addComment(new Comment("Muy bueno wacho", 7, magoDeTerramar, fede));
        magoDeTerramar.addComment(new Comment("Fidel rompia las bolas con esto", 4, magoDeTerramar, elias));
        this.bookService.save(magoDeTerramar);

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

    public void setGoogleBookService(final GoogleBookService googleBookService) {
        this.googleBookService = googleBookService;
    }

    public GoogleBookService getGoogleBookService() {
        return this.googleBookService;
    }

}
