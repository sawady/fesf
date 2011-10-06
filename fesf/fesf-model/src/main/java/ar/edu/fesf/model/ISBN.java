package ar.edu.fesf.model;

import ar.edu.fesf.model.validations.ISBNValidator;

public class ISBN extends Entity {

    private static final long serialVersionUID = 1L;

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {

        new ISBNValidator().checkISBN(value);

        this.value = value;
    }

}
