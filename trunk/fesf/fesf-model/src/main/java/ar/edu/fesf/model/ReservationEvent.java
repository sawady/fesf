package ar.edu.fesf.model;

public class ReservationEvent extends Event {

    /* Methods */
    @Override
    public void updateUserCategories() {
        this.getPerson().getCategories().addAll(this.getBook().getCategories());
    }

}
