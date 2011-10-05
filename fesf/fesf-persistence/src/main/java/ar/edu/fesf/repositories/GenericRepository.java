package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for generic DAO
 * 
 * @param <T>
 */
public interface GenericRepository<T> extends Serializable {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void deleteById(Serializable id);

    int count();

    T findByEquality(T object);

    List<T> findByExample(T exampleObject);

    T findByPropertyUnique(final String property, final Object object);

    List<T> findByProperty(final String property, final Object object);

    List<T> findLikeProperty(final String property, final String pattern);

    Iterator<T> getIterator();

}