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

    List<T> findByExample(T exampleObject);

    T findByProperty(final String property, final Object object);

    Iterator<T> getIterator();

}