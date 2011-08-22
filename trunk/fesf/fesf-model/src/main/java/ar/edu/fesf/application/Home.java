package ar.edu.fesf.application;

import java.util.List;

public interface Home<T extends Persistible> {

    // ********************************************************
    // ** ABM
    // ********************************************************

    void add(T object);

    void delete(T object);

    void update(T object);

    void deleteAllEntities();

    // ********************************************************
    // ** SEARCH
    // ********************************************************

    T getWithId(int id);

    List<T> getAllEntities();

}
