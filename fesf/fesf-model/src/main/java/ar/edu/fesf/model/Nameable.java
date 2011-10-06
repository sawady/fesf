package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Nameable extends Entity {

    private static final long serialVersionUID = 1L;

    protected String name;

    /* Accessors */
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        checkNotNull(name);
        checkArgument(!name.isEmpty(), "The name cannot be empty");
        this.name = name;
    }

}
