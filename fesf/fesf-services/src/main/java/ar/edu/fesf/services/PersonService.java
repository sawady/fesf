package ar.edu.fesf.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.repositories.PersonRepository;

/**
 * TODO: description
 */
public class PersonService {

    private PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return this.personRepository;
    }

    public void setPersonRepository(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPerson() {
        return this.personRepository.findAll();
    }

    @Transactional
    public void addPerson(final Person person) {
        this.personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> filterPeople(final String pattern) {
        return this.personRepository.filterPeople(pattern);
    }
}
