package ar.edu.fesf.services;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;

public class PersonService extends GenericTransactionalRepositoryService<Person> implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    @Transactional
    public Person findPersonWithUserInfo(final UserInfo userinfo) {
        return this.getRepository().findByPropertyUnique("userinfo", userinfo);
    }

}
