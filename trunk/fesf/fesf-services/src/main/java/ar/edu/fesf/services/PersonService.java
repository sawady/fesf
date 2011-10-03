package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.PersonRepository;

public class PersonService extends GenericTransactionalRepositoryService<Person> implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    @Transactional(readOnly = true)
    public List<Person> filterPeople(final String pattern) {
        return ((PersonRepository) this.getRepository()).filterPeople(pattern);
    }

    @Transactional
    public Person findPersonWithUserInfo(final UserInfo userinfo) {
        return this.getRepository().findByProperty("userinfo", userinfo);
    }

}
