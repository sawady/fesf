package ar.edu.fesf.dtos;

public class NewBookDTO extends EditBookDTO {

    private static final long serialVersionUID = 1L;

    private int countOfCopies;

    public void setCountOfCopies(final int countOfCopies) {
        this.countOfCopies = countOfCopies;
    }

    public int getCountOfCopies() {
        return this.countOfCopies;
    }

}
