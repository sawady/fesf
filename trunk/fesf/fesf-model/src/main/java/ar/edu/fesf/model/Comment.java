package ar.edu.fesf.model;

import ar.edu.fesf.validations.NotNullFieldValidator;

public class Comment extends Event {

    private String body;

    /* Accessors */

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        NotNullFieldValidator.validate(body, "Body");
        this.body = body;
    }

}
