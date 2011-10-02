package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.PersonRepository;
import ar.edu.fesf.repositories.UserInfoRepository;

public class PersonService implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    private UserInfoRepository userInfoRepository;

    private PersonRepository personRepository;

    public UserInfoRepository getUserInfoRepository() {
        return this.userInfoRepository;
    }

    public void setUserInfoRepository(final UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public PersonRepository getPersonRepository() {
        return this.personRepository;
    }

    public void setPersonRepository(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPerson() {
        return this.getPersonRepository().findAll();
    }

    @Transactional
    public void addPerson(final Person person) {
        this.getPersonRepository().save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> filterPeople(final String pattern) {
        return this.getPersonRepository().filterPeople(pattern);
    }

    @Transactional
    public Person findPersonWithUserInfo(final UserInfo userinfo) {
        return this.getPersonRepository().findByProperty("userinfo", userinfo);
    }

}
