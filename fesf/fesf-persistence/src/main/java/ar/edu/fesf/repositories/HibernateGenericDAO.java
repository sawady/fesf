package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Generic hibernate DAO
 * 
 * @param <T>
 */
public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T> {

    private static final long serialVersionUID = 1972615186739943794L;

    protected Class<T> persistentClass = this.getDomainClass();

    protected abstract Class<T> getDomainClass();

    @Override
    public void save(final T entity) {
        this.getHibernateTemplate().save(entity);
        this.getHibernateTemplate().flush();
    }

    @Override
    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(final Serializable id) {
        return (T) this.getHibernateTemplate().get(this.persistentClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");

    }

    @Override
    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = this.getHibernateTemplate().find(
                "select count(*) from " + this.persistentClass.getName() + " o");
        Long count = list.get(0);
        return count.intValue();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    @Override
    public Iterator<T> getIterator() {
        return this.findAll().iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByPropertyUnique(final String property, final Object object) {
        return (T) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public T doInHibernate(final Session session) throws HibernateException, SQLException {
                final Criteria criteria = session.createCriteria(this.getClass());
                criteria.add(Restrictions.eq(property, object));
                return (T) criteria.uniqueResult();
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByProperty(final String property, final Object object) {
        return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(this.getClass());
                criteria.add(Restrictions.eq(property, object));
                return criteria.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findLikeProperty(final String property, final String pattern) {
        return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(this.getClass());
                criteria.add(Restrictions.like(property, "%" + pattern + "%"));
                return criteria.list();
            }
        });
    }

}