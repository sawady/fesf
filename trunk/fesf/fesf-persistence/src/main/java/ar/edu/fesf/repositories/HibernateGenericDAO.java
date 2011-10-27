package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.edu.fesf.model.Entity;

/**
 * Generic hibernate DAO
 * 
 * @param <T>
 */
public abstract class HibernateGenericDAO<T extends Entity> extends HibernateDaoSupport implements
        IGenericRepository<T> {

    private static final String UNCHECKED = "unchecked";

    private static final long serialVersionUID = 1972615186739943794L;

    protected Class<T> persistentClass = this.getDomainClass();

    protected abstract Class<T> getDomainClass();

    @Override
    public void save(final T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    @Override
    @SuppressWarnings(UNCHECKED)
    public List<T> findAll() {
        return this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
    }

    @Override
    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @Override
    public <P> P initialize(final P toIntialize) {
        Hibernate.initialize(toIntialize);
        return toIntialize;
    }

    @Override
    public <P> Collection<P> initialize(final Collection<P> collectionToInitialize) {
        for (P p : collectionToInitialize) {
            Hibernate.initialize(p);
        }
        Hibernate.initialize(collectionToInitialize);
        return collectionToInitialize;
    }

    @Override
    @SuppressWarnings(UNCHECKED)
    public int count() {
        List<Long> list = this.getHibernateTemplate().find(
                "select count(*) from " + this.persistentClass.getName() + " o");
        Long count = list.get(0);
        return count.intValue();

    }

    @Override
    @SuppressWarnings(UNCHECKED)
    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);
    }

    @Override
    public Iterator<T> getIterator() {
        return this.findAll().iterator();
    }

    @Override
    public T findByEquality(final T object) {
        return this.findById(object.getId());
    }

    @Override
    public T findByPropertyUnique(final String property, final Object object) {
        List<T> results = this.findByProperty(property, object);
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings(UNCHECKED)
    protected List<T> findBy(final Criterion rec) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(HibernateGenericDAO.this.getDomainClass());
                criteria.add(rec);
                return criteria.list();
            }
        });
    }

    @SuppressWarnings(UNCHECKED)
    protected List<T> findBy(final Criterion rec, final Order order, final int maxResults) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(HibernateGenericDAO.this.getDomainClass());
                criteria.add(rec);
                criteria.addOrder(order);
                criteria.setMaxResults(maxResults);
                return criteria.list();
            }
        });
    }

    @SuppressWarnings(UNCHECKED)
    protected List<T> findBy(final Criterion rec, final Order order) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(HibernateGenericDAO.this.getDomainClass());
                criteria.add(rec);
                criteria.addOrder(order);
                return criteria.list();
            }
        });
    }

    @Override
    public List<T> findByProperty(final String property, final Object object) {
        return this.findBy(Restrictions.eq(property, object));
    }

    @Override
    public List<T> findByPropertyLike(final String property, final String pattern) {
        return this.findBy(Restrictions.ilike(property, "%" + pattern + "%"));
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    public void setPersistentClass(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

}