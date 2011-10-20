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
import ar.edu.fesf.repositories.LoanRepository;
import ar.edu.fesf.repositories.PersonRepository;
import ar.edu.fesf.repositories.UserInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class PersonRepositoryTest {

    private List<Person> peopleToPersist;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LoanRepository loanRepository;

    private Person pepe;

    private Loan pepeLoan;

    @Before
    public void setUp() {

        this.peopleToPersist = new ArrayList<Person>();

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
        this.peopleToPersist.add(this.pepe);
        this.peopleToPersist.add(new PersonBuilder().withName("Carlos").build());
        this.peopleToPersist.add(new PersonBuilder().withName("Soprano").build());
        this.peopleToPersist.add(new PersonBuilder().withName("Cesar").build());

        Author author1 = new Author();
        author1.setName("Pablo Funes");
        Author author2 = new Author();
        author2.setName("Guille Mori");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editorial Amboro");

        Book book = new BookBuilder().withTitle("Un Mago de Terramar").withCategory(drama).withCategory(aventura)
                .withAuthor(author1).withAuthor(author2).withPublisher(publisher1).withCountOfCopies(1).build();

        this.pepeLoan = new LoanBuilder().withAgreedReturnDate(new DateTime().plus(10)).withBookCopy(book)
                .withPerson(this.pepe).build();
        this.pepe.addNewLoan(this.pepeLoan);

        for (Person person : this.peopleToPersist) {
            this.personRepository.save(person);
        }

        this.personRepository.getHibernateTemplate().flush();
        this.personRepository.getHibernateTemplate().clear();

    }

    @Test
    public void count() {
        assertEquals("Must be equals 4", this.personRepository.count(), 4);
    }

    @Test
    public void findByName() {
        Person pepePorNombre = this.personRepository.findByPropertyUnique("name", this.pepe.getName());
        Person pepePorID = this.personRepository.findById(this.pepe.getId());
        assertEquals("Must be the same Pepe", pepePorNombre, pepePorID);
    }

    @Test
    public void findByExample() {
        Person pepeExample = this.personRepository.findByExample(this.pepe).get(0);
        Person pepePorID = this.personRepository.findById(this.pepe.getId());
        assertEquals("Must be the same Pepe", pepeExample, pepePorID);
    }

    @Test
    public void mappings() {
        Person pepeEncontrado = this.personRepository.findByExample(this.pepe).get(0);
        Loan pepeLoan = this.loanRepository.findByEquality(this.pepeLoan);

        assertEquals("Must have same userInfo", this.pepe.getUserInfo().getUserid(), pepeEncontrado.getUserInfo()
                .getUserid());
        assertEquals("Must have same userInfo", this.pepe.getUserInfo().getPass(), pepeEncontrado.getUserInfo()
                .getPass());
        assertEquals("Must have same userInfo", this.pepe.getUserInfo().getRole(), pepeEncontrado.getUserInfo()
                .getRole());
        assertEquals("Must have same email", "sarasa@gmail.com", pepeEncontrado.getEmail().getValue());
        assertEquals("Must have same address", "colon 355", pepeEncontrado.getAddress());
        assertTrue("Must have his loan", pepeEncontrado.getCurrentLoans().contains(pepeLoan));
    }

    @Test
    public void currentLoans() {
        Person pepeEncontrado = this.personRepository.findByEquality(this.pepe);
        Loan pepeLoan = this.loanRepository.findByEquality(this.pepeLoan);

        for (Loan loan : pepeEncontrado.getCurrentLoans()) {
            assertEquals("Must be pepe", pepeEncontrado, loan.getPerson());
        }

        assertTrue("Must have pepe loan", pepeEncontrado.getCurrentLoans().contains(pepeLoan));
    }

    @Test
    public void currentLoan() {
        assertEquals("Must be pepes loan", this.pepeLoan, this.pepe.getCurrentLoans().get(0));
    }

    public void setPersonsToPersist(final List<Person> personsToPersist) {
        this.peopleToPersist = personsToPersist;
    }

    public List<Person> getPersonsToPersist() {
        return this.peopleToPersist;
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

    public void setUserInfoRepository(final UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfoRepository getUserInfoRepository() {
        return this.userInfoRepository;
    }

    public void setLoanRepository(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanRepository getLoanRepository() {
        return this.loanRepository;
    }

}
