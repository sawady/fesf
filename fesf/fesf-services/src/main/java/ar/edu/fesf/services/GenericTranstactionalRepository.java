package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import ar.edu.fesf.repositories.GenericRepository;

public interface GenericTranstactionalRepository<T> extends Serializable {

    GenericRepository<T> getRepository();

    void setRepository(final GenericRepository<T> genericRepository);

    void save(final T entity);

    void delete(final T entity);

    T findById(final Serializable id);

    List<T> findAll();

    void deleteById(final Serializable id);

    int count();

    List<T> findByExample(final T exampleObject);

    T findByPropertyUnique(final String property, final Object object);

    List<T> findByProperty(final String property, final Object object);

    List<T> findLikeProperty(final String property, final String pattern);

    Iterator<T> getIterator();

    T findByEquality(final T object);

}