package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;

public class PersonService extends GenericTransactionalRepositoryService<Person> implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    @Transactional
    public Person findPersonWithUserInfo(final UserInfo userinfo) {
        return this.getRepository().findByPropertyUnique("userinfo", userinfo);
    }

    public void initialize() {
        this.save(new Person("Pepe"));
        this.save(new Person("Carlos"));
        this.save(new Person("Tomas"));
        this.save(new Person("Eustaquio"));
        this.save(new Person("Delincuente"));
        this.save(new Person("Gomez"));
        this.save(new Person("Klose"));
        this.save(new Person("Mariano"));
        this.save(new Person("Niembro"));
    }

    public String getFieldForSort() {
        return "name";
    }

    public List<String> getFieldNames() {
        List<String> names = new ArrayList<String>();
        names.add("name");
        return names;
    }

}
