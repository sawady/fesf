package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.ValidatorRequiredField;

public class Comment extends Entity {

    private String body;

    /* Accessors */

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        ValidatorRequiredField.validate(body, "Body");
        this.body = body;
    }

}
