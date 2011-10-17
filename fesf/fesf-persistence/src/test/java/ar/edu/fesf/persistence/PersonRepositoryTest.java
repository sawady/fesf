package ar.edu.fesf.persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class PersonRepositoryTest extends AbstractDependencyInjectionSpringContextTests {

    private List<Person> personsToPersist;

    private PersonRepository personRepository;

    @Test
    @Transactional
    public void saveTest() {

        this.personsToPersist = new ArrayList<Person>();

        this.personsToPersist.add(new PersonBuilder().withName("Pepe").build());
        this.personsToPersist.add(new PersonBuilder().withName("Carlos").build());
        this.personsToPersist.add(new PersonBuilder().withName("Soprano").build());
        this.personsToPersist.add(new PersonBuilder().withName("Cesar").build());

        for (Person person : this.personsToPersist) {
            this.personRepository.save(person);
        }

        assertEquals(this.personRepository.count(), 4);
        assertTrue(this.personsToPersist.containsAll(this.personRepository.findAll()));
        assertEquals(this.personsToPersist.get(0), this.personRepository.findByPropertyUnique("name", "Pepe"));

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

}
