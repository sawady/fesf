package ar.edu.fesf.application;

import java.util.List;

public interface Home<T extends Persistible> {

    // ********************************************************
    // ** ABM
    // ********************************************************

    void agregar(T object);

    void eliminar(T object);

    void actualizar(T object);

    void deleteAllEntities();

    // ********************************************************
    // ** SEARCH
    // ********************************************************

    T getWithId(int id);

    List<T> getAllEntities();

    T getNombrable(String nombre);

}
