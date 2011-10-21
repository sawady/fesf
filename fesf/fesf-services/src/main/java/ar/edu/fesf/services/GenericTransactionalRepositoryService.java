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

    /**
     * Don't use with fields that represent collections.
     * 
     * Don't be gil.
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public <P> P getField(final T entity, final String methodName) {
        T entityDB = this.getRepository().findByEquality(entity);
        Field field = ReflectionUtils.findField(entityDB.getClass(), methodName);
        field.setAccessible(true);
        return this.getRepository().initialize((P) ReflectionUtils.getField(field, entityDB));
    }

    @Override
    @Transactional(readOnly = true)
    public <P> Collection<P> getCollectionField(final T obj, final String methodName) {
        return this.getRepository().initialize(this.<Collection<P>> getField(obj, methodName));
    }
}
