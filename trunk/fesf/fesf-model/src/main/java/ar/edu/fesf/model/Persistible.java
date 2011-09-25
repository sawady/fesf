package ar.edu.fesf.model;

public interface Persistible {

    int getId();

    void setId(int id);

    int getVersion();

    void setVersion(int version);

}
