package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.ValidatorString;

public abstract class Nameable extends Entity {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        ValidatorString.validate(name, "Name");
        this.name = name;
    }

}
