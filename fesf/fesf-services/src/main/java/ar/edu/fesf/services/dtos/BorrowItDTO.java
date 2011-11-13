package ar.edu.fesf.services.dtos;

import java.io.Serializable;

public class BorrowItDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String agreedReturnDate;

    public BorrowItDTO() {
        super();
    }

    public BorrowItDTO(final String anAgreedReturnDate) {
        super();
        this.agreedReturnDate = anAgreedReturnDate;
    }

    public void setAgreedReturnDate(final String agreedReturnDate) {
        this.agreedReturnDate = agreedReturnDate;
    }

    public String getAgreedReturnDate() {
        return this.agreedReturnDate;
    }

}
