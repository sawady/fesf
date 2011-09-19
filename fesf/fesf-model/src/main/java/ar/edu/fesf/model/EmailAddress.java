package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

public class EmailAddress {

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
