package ar.edu.fesf.application;

public interface Persistible {

    /* METODOS///////////////////////////////////////////////////////////////////////////////// */

    int getId();

    void setId(int id);

    int getVersion();

    void setVersion(int version);

}