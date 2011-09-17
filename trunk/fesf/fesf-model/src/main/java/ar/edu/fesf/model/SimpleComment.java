package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleComment extends Event {

    private String body;

    /* Accessors */

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        checkNotNull(body);
        this.body = body;
    }

}
