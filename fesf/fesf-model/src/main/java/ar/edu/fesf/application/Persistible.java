package ar.edu.fesf.application;

public interface Persistible {

    /* METODOS///////////////////////////////////////////////////////////////////////////////// */

    int getId();

    void setId(int id);

    void printValues();

    int getVersion();

    void setVersion(int version);

}
