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

    @Override
    public int hashCode() {
        final int prime = 31;
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
        EmailAddress other = (EmailAddress) obj;
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
