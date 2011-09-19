package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

public class ISBN {

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        checkArgument(this.isValid(value), "Not valid ISBN");
        this.value = value;
    }

    private boolean isValid(final String isbn) {
        // TODO Complete this definition
        return isbn.length() == 10;
    }
}
