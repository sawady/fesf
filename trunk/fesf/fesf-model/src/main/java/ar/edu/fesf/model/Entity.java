package ar.edu.fesf.model;

public abstract class Entity implements Persistible {

    /* VARIABLES ************************************************** */

    private int id;

    private int version;

    /* CONSTRUCTOR ************************************************** */

    public Entity() {
        super();
    }

    public Entity(final int id) {
        this.id = id;
    }

    /* GET&SET************************************************** */

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(final int version) {
        this.version = version;
    }

}