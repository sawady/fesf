package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;

public class EmailAddress extends Entity implements Serializable {

    private static final long serialVersionUID = 4605094628949461850L;

    public EmailAddress(final String value) {
        super();
        this.value = value;
    }

    public EmailAddress() {
        super();
    }

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        checkArgument(value.contains("@") && value.substring(value.indexOf('@')).contains("."),
                "Email address is not valid");
        this.value = value;
    }

}
