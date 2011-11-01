package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import ar.edu.fesf.model.Person;

public class PersonRepository extends HibernateGenericDAO<Person> implements Serializable {

    private static final long serialVersionUID = 6221677389349843898L;

    @Override
    protected Class<Person> getDomainClass() {
        return Person.class;
    }

    public Person findByEmail(final String email) {
        Query query = this.getSession().createQuery(
                "select distinct(person) from " + this.persistentClass.getName()
                        + " person join person.email email where email.value = " + "'" + email + "'");

        return (Person) query.uniqueResult();
    }

    public List<Person> getLoanees() {
        List<Person> loanees = new ArrayList<Person>();
        for (Person person : this.findAll()) {
            if (person.isLoanee()) {
                loanees.add(person);
            }
        }
        return loanees;
    }

}
