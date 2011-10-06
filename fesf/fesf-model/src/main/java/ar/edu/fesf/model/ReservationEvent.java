package ar.edu.fesf.model;

public class ReservationEvent extends Event {

    private static final long serialVersionUID = 1L;

    /* Methods */
    @Override
    public void updateUserCategories() {
        this.getPerson().getCategories().addAll(this.getBook().getCategories());
    }

}
