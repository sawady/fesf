package ar.edu.fesf.model;

import ar.edu.fesf.model.validations.ISBNValidator;

public class ISBN extends Entity {

    private static final long serialVersionUID = 1L;

    private String value;

    public ISBN(final String value) {
        super();
        this.value = value;
    }

    public ISBN() {
        super();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {

        new ISBNValidator().checkISBN(value);

        this.value = value;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (this.value == null ? 0 : this.value.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ISBN other = (ISBN) obj;
        if (this.value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!this.value.equals(other.value)) {
            return false;
        }
        return true;
    }

}
