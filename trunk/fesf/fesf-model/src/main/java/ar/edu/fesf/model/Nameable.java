package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.NotEmptyStringValidator;

public abstract class Nameable extends Entity {

    private String name;

    /* Accessors */
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        NotEmptyStringValidator.validate(name, "Name");
        this.name = name;
    }

}
