package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.fesf.application.Entity;

public abstract class Nameable extends Entity {

    private String name;

    /* Accessors */
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        checkArgument(!name.isEmpty(), "The name cannot be empty");
        this.name = name;
    }

}
