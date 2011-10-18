package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.EmailAddress;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class PersonRepositoryTest {

    private List<Person> personsToPersist;

    @Autowired
    private PersonRepository personRepository;

    private Person pepe;

    private Loan pepeLoan;

    @Before
    public void setUp() {

        this.personsToPersist = new ArrayList<Person>();

        Set<Category> categories = new HashSet<Category>();

        Category drama = new Category("Drama");
        categories.add(drama);
        Category aventura = new Category("Aventura");
        categories.add(aventura);
        categories.add(new Category("Romance"));
        categories.add(new Category("Terror"));

        UserInfo anUserInfo = new UserInfo("pepe", "pass", Role.LIBRARIAN);
        this.pepe = new PersonBuilder().withName("Pepe").withUserInfo(anUserInfo).withAddress("colon 355").withAge(18)
                .withSurname("Sorias").withCategories(categories).withEmail(new EmailAddress("sarasa@gmail.com"))
                .build();
        this.personsToPersist.add(this.pepe);
        this.personsToPersist.add(new PersonBuilder().withName("Carlos").build());
        this.personsToPersist.add(new PersonBuilder().withName("Soprano").build());
        this.personsToPersist.add(new PersonBuilder().withName("Cesar").build());

        Author author1 = new Author();
        author1.setName("Pablo Funes");
        Author author2 = new Author();
        author2.setName("Guille Mori");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editorial Amboro");

        Book book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(1).build();

        this.pepeLoan = new LoanBuilder().withAgreedReturnDate(new DateTime().plus(10)).withBookCopy(book).build();
        this.pepe.addNewLoan(this.pepeLoan);

        for (Person person : this.personsToPersist) {
            this.personRepository.save(person);
        }

    }

    @Test
    public void count() {
        assertEquals("Must be equals 4", this.personRepository.count(), 4);
    }

    @Test
    public void findById() {
        Person pepeEncontrado = this.personRepository.findById(this.pepe.getId());
        assertEquals("Must be equals Pepe", this.pepe, pepeEncontrado);
    }

    @Test
    public void findByName() {
        Person pepeEncontrado = this.personRepository.findByPropertyUnique("name", this.pepe.getName());
        assertEquals("Must be equals Pepe", this.pepe, pepeEncontrado);
    }

    @Test
    public void findByExample() {
        Person pepeEncontrado = this.personRepository.findByExample(this.pepe).get(0);
        assertEquals("Must be equals Pepe", this.pepe, pepeEncontrado);
    }

    @Test
    public void mappings() {
        Person pepeEncontrado = this.personRepository.findByExample(this.pepe).get(0);
        assertTrue("Must contain all the persons in the list",
                this.personsToPersist.containsAll(this.personRepository.findAll()));
        assertEquals("Must have same userInfo", this.pepe.getUserInfo(), pepeEncontrado.getUserInfo());
        assertEquals("Must have same email", new EmailAddress("sarasa@gmail.com"), pepeEncontrado.getEmail());
        assertEquals("Must have same address", "colon 355", pepeEncontrado.getAddress());
        assertTrue("Must have his loan", pepeEncontrado.getCurrentLoans().contains(this.pepeLoan));
    }

    public void setPersonsToPersist(final List<Person> personsToPersist) {
        this.personsToPersist = personsToPersist;
    }

    public List<Person> getPersonsToPersist() {
        return this.personsToPersist;
    }

    public void setPersonRepository(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonRepository getPersonRepository() {
        return this.personRepository;
    }

    public Person getPepe() {
        return this.pepe;
    }

    public void setPepe(final Person pepe) {
        this.pepe = pepe;
    }

    public Loan getPepeLoan() {
        return this.pepeLoan;
    }

    public void setPepeLoan(final Loan pepeLoan) {
        this.pepeLoan = pepeLoan;
    }

}
