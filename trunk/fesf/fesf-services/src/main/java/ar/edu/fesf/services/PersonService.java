package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.EmailAddress;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.repositories.PersonRepository;
import ar.edu.fesf.services.dtos.PersonDTO;

public class PersonService extends GenericTransactionalRepositoryService<Person> implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    private PersonRepository getPersonRepository() {
        return (PersonRepository) this.getRepository();
    }

    @Transactional(readOnly = true)
    public Person initializeLoaneeInfo(final Person loanee) {
        return this.initializeFields(loanee, "currentLoans", "email");
    }

    public List<String> getFieldNames() {
        List<String> names = new ArrayList<String>();
        names.add("name");
        return names;
    }

    public List<String> getFieldsToSort() {
        List<String> names = new ArrayList<String>();
        names.add("name");
        return names;
    }

    public Role[] getRoles() {
        return Role.values();
    }

    public List<String> getRolesOf(final Person person) {

        List<String> roles = new ArrayList<String>();

        Role userRole = person.getRole();

        for (Role role : Role.values()) {
            if (role.isInferior(userRole)) {
                roles.add(role.toString());
            }
        }

        return roles;
    }

    @Transactional(readOnly = true)
    public Person findPersonByEmail(final String email) {
        return this.getPersonRepository().findByEmail(email);
    }

    @Transactional
    public Person registerPerson(final PersonDTO personDTO) {
        Person personDB = this.findById(personDTO.getId());
        if (personDB == null) { // No esta en la base
            personDB = this.registerNewPerson(personDTO);

        } else {
            personDB = this.registerEditPerson(personDTO);
        }
        return personDB;
    }

    @Transactional(readOnly = true)
    public Person initializePersonInfo(final Person person) {
        return this.initializeFields(person, "email");
    }

    @Transactional
    public Person registerNewPerson(final PersonDTO personDTO) {
        Person newPerson = new PersonBuilder().withName(personDTO.getName()).withSurname(personDTO.getSurname())
                .withAge(personDTO.getAge()).withPhone(personDTO.getPhone()).withAddress(personDTO.getAddress())
                .withRole(Role.USER).withEmail(new EmailAddress(personDTO.getEmail())).build();
        this.save(newPerson);
        return newPerson;
    }

    @Transactional
    public Person registerEditPerson(final PersonDTO personDTO) {

        Person personDB = this.findById(personDTO.getId());

        personDB.setName(personDTO.getName());
        personDB.setSurname(personDTO.getSurname());
        personDB.setAge(personDTO.getAge());
        personDB.setPhone(personDTO.getPhone());
        personDB.setAddress(personDTO.getAddress());
        personDB.setRole(Role.USER); // TODO ver tema
                                     // de roles
        personDB.setEmail(new EmailAddress(personDTO.getEmail()));

        this.save(personDB);

        return personDB;

    }

    @Transactional(readOnly = true)
    public List<Person> getLoanees() {
        return this.getPersonRepository().getLoanees();
    }

    @Transactional
    public List<Loan> getCurrentLoans(final Person loanee) {
        List<Loan> loans = new ArrayList<Loan>();
        loans.addAll(this.findById(loanee.getId()).getCurrentLoans());
        return loans;
    }
}
