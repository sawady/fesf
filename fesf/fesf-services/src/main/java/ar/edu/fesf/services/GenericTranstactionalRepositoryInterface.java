package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.repositories.GenericRepository;

public interface GenericTranstactionalRepositoryInterface<T> extends Serializable {

    GenericRepository<T> getRepository();

    void setRepository(final GenericRepository<T> genericRepository);

    @Transactional
    void save(final T entity);

    @Transactional
    void delete(final T entity);

    @Transactional
    void update(final T entity);

    @Transactional(readOnly = true)
    T findById(final Serializable id);

    @Transactional(readOnly = true)
    List<T> findAll();

    @Transactional
    void deleteById(final Serializable id);

    @Transactional
    int count();

    @Transactional(readOnly = true)
    List<T> findByExample(final T exampleObject);

    @Transactional(readOnly = true)
    T findByProperty(final String property, final Object object);

    Iterator<T> getIterator();

}