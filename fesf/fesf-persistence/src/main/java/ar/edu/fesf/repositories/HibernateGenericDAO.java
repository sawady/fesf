package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.sql.SQLException;
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

    @SuppressWarnings("unchecked")
    public T findByProperty(final String property, final Object userinfo) {
        return (T) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public T doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(this.getClass());
                criteria.add(Restrictions.eq(property, userinfo));
                return (T) criteria.uniqueResult();
            }
        });
    }

}