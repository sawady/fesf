package ar.edu.fesf.services;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import ar.edu.fesf.model.Entity;
import ar.edu.fesf.repositories.IGenericRepository;

public class GenericTransactionalRepositoryService<T extends Entity> implements IGenericTranstactionalRepository<T> {

    private static final long serialVersionUID = -6913034947685604978L;

    private IGenericRepository<T> repository;

    @Override
    public IGenericRepository<T> getRepository() {
        return this.repository;
    }

    @Override
    public void setRepository(final IGenericRepository<T> genericRepository) {
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Iterator<T> getIterator() {
        return this.getRepository().getIterator();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByPropertyLike(final String property, final String pattern) {
        return this.getRepository().findByPropertyLike(property, pattern);
    }

    @Override
    @Transactional(readOnly = true)
    public T findByEquality(final T object) {
        return this.getRepository().findByEquality(object);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    private <P> void initializeField(final T entityDB, final String fieldName) {
        Field field = ReflectionUtils.findField(entityDB.getClass(), fieldName);
        field.setAccessible(true);
        Object fieldValue = ReflectionUtils.getField(field, entityDB);
        if (Collection.class.isAssignableFrom(field.getType())) {
            this.getRepository().initialize((Collection<P>) fieldValue);
        } else {
            this.getRepository().initialize((P) fieldValue);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T initializeFields(final T obj, final String... fieldNames) {
        T entityDB = this.getRepository().findByEquality(obj);

        for (String field : fieldNames) {
            this.initializeField(entityDB, field);
        }

        return entityDB;
    }
}
