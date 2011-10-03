package ar.edu.fesf.model;

import java.io.Serializable;

public abstract class Entity implements Persistible, Serializable {

    /* VARIABLES ************************************************** */

    private static final long serialVersionUID = -7830644979354059015L;

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