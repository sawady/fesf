package ar.edu.fesf.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for generic DAO
 * 
 * @param <T>
 */
public interface IGenericRepository<T> extends Serializable {

    void save(T entity);

    void delete(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void deleteById(Serializable id);

    int count();

    T findByEquality(T object);

    List<T> findByExample(T exampleObject);

    T findByPropertyUnique(final String property, final Object object);

    List<T> findByProperty(final String property, final Object object);

    List<T> findByPropertyLike(final String property, final String pattern);

    Iterator<T> getIterator();

    <P> P initialize(P toIntialize);

    <P> Collection<P> initialize(Collection<P> collectionToInitialize);

    <P> Collection<P> initialize(Collection<P> collectionToInitialize, int countOfComments);

}