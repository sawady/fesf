package ar.edu.fesf;

import java.util.List;

public interface Home<T extends Persistible> {

    // ********************************************************
    // ** ABM
    // ********************************************************

    public void agregar(T object);

    public void eliminar(T object);

    public void actualizar(T object);

    public void deleteAllEntities();

    // ********************************************************
    // ** SEARCH
    // ********************************************************

    public T getWithId(int id);

    public List<T> getAllEntities();

    public T getNombrable(String nombre);

}
