package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.repositories.GenericRepository;

public class GenericTransactionalRepositoryService<T> implements GenericTranstactionalRepository<T> {

    private static final long serialVersionUID = -6913034947685604978L;

    private GenericRepository<T> repository;

    @Override
    public GenericRepository<T> getRepository() {
        return this.repository;
    }

    @Override
    public void setRepository(final GenericRepository<T> genericRepository) {
        this.repository = genericRepository;
    }

    @Override
    @Transactional
    public void save(final T entity) {
        this.getRepository().save(entity);
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        this.getRepository().delete(entity);
    }

    @Override
    @Transactional
    public void update(final T entity) {
        this.getRepository().update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(final Serializable id) {
        return this.getRepository().findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    @Override
    @Transactional
    public void deleteById(final Serializable id) {
        this.getRepository().deleteById(id);
    }

    @Override
    @Transactional
    public int count() {
        return this.getRepository().count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByExample(final T exampleObject) {
        return this.getRepository().findByExample(exampleObject);
    }

    @Override
    @Transactional(readOnly = true)
    public T findByPropertyUnique(final String property, final Object object) {
        return this.getRepository().findByPropertyUnique(property, object);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByProperty(final String property, final Object object) {
        return this.getRepository().findByProperty(property, object);
    }

    @Override
    @Transactional
    public Iterator<T> getIterator() {
        return this.getRepository().getIterator();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findLikeProperty(final String property, final String pattern) {
        return this.getRepository().findLikeProperty(property, pattern);
    }

}
