package ar.edu.fesf;

public interface Persistible {

    /* METODOS///////////////////////////////////////////////////////////////////////////////// */

    public int getId();

    public void setId(int id);

    public abstract void printValues();

    public int getVersion();

    public void setVersion(int version);

}
