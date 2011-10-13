package ar.edu.fesf.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.repositories.PersonRepository;

public class PersonRepositoryTest {

    private List<Person> personsToPersist;

    private PersonRepository personRepository;

    @BeforeClass
    public void setUpClass() {
        this.personsToPersist = new ArrayList<Person>();

        this.personsToPersist.add(new PersonBuilder().withName("Pepe").build());
        this.personsToPersist.add(new PersonBuilder().withName("Carlos").build());
        this.personsToPersist.add(new PersonBuilder().withName("Soprano").build());
        this.personsToPersist.add(new PersonBuilder().withName("Cesar").build());
    }

    @Test
    public void saveTest() {

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
