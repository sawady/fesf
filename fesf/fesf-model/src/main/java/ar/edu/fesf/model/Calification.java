package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

public class Calification extends Entity {

    private static final long serialVersionUID = 1L;

    private int userCalification;

    public int getUserCalification() {
        return this.userCalification;
    }

    public void setUserCalification(final int calification) {
        checkArgument(calification > 0 && calification <= 10, "The calification must be a number between 0 and 10");
        this.userCalification = calification;
    }

}
