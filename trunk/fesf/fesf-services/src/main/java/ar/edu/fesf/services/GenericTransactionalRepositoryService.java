package ar.edu.fesf.services;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.repositories.GenericRepository;

public class GenericTransactionalRepositoryService<T> implements GenericTranstactionalRepositoryInterface<T> {

    private static final long serialVersionUID = -6913034947685604978L;

    protected GenericRepository<T> repository;

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
        this.repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        this.repository.delete(entity);
    }

    @Override
    @Transactional
    public void update(final T entity) {
        this.repository.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(final Serializable id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(final Serializable id) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public int count() {
        return this.repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByExample(final T exampleObject) {
        return this.repository.findByExample(exampleObject);
    }

    @Override
    @Transactional(readOnly = true)
    public T findByProperty(final String property, final Object object) {
        return this.repository.findByProperty(property, object);
    }

    @Override
    public Iterator<T> getIterator() {
        return this.repository.getIterator();
    }

}
