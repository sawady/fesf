package ar.edu.fesf.repositories;

import java.io.Serializable;

import ar.edu.fesf.model.Person;

public class PersonRepository extends HibernateGenericDAO<Person> implements Serializable {

    private static final long serialVersionUID = 6221677389349843898L;

    @Override
    protected Class<Person> getDomainClass() {
        return Person.class;
    }

}
