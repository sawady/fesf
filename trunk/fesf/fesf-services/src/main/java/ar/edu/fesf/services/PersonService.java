package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.builders.PersonBuilder;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.PersonRepository;

public class PersonService extends GenericTransactionalRepositoryService<Person> implements Serializable {

    private static final long serialVersionUID = 281627290258132217L;

    @Transactional
    public Person findPersonWithUserInfo(final UserInfo userinfo) {
        return this.getRepository().findByPropertyUnique("userinfo", userinfo);
    }

    public void initialize() {
        this.save(new Person("Ariel"));
        this.save(new PersonBuilder().withName("Jose").withUserInfo(new UserInfo("jose", "jose", Role.LIBRARIAN))
                .build());
        this.save(new PersonBuilder().withName("Pepe").withUserInfo(new UserInfo("pepe", "pepe", Role.LIBRARIAN))
                .build());
        this.save(new PersonBuilder().withName("Carlos").withUserInfo(new UserInfo("carlos", "carlos", Role.USER))
                .build());
        this.save(new PersonBuilder().withName("Tomas").withUserInfo(new UserInfo("tomas", "tomas", Role.USER)).build());
        this.save(new PersonBuilder().withName("matias").withUserInfo(new UserInfo("matias", "matias", Role.USER))
                .build());
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

    @Transactional
    public List<Person> getLoanees() {
        return ((PersonRepository) this.getRepository()).getLoanees();
    }
}
