package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

public class Calification extends Entity {

    private static final long serialVersionUID = 1L;

    private int value;

    public Calification() {
        super();
    }

    public Calification(final Integer califNum) {
        super();
        this.value = califNum;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int calification) {
        checkArgument(calification > 0 && calification <= 10, "The calification must be a number between 0 and 10");
        this.value = calification;
    }

}
