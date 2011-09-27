package ar.edu.fesf.repositories;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import ar.edu.fesf.model.Person;

public class PersonRepository extends HibernateGenericDAO<Person> {

    @Override
    protected Class<Person> getDomainClass() {
        return Person.class;
    }

    @SuppressWarnings("unchecked")
    public List<Person> filterPeople(final String pattern) {
        return (List<Person>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<Person> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Person.class);
                criteria.add(Restrictions.like("name", "%" + pattern + "%"));
                return criteria.list();
            }
        });
    }
}
