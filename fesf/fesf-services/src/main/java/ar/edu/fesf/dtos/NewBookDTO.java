package ar.edu.fesf.dtos;

public class NewBookDTO extends EditBookDTO {

    private static final long serialVersionUID = 1L;

    private int countOfCopies;

    public NewBookDTO() {
        super();
    }

    public NewBookDTO(final String title, final String isbn, final String publisher, final String description,
            final boolean available, final int countOfCopies) {
        super();
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.description = description;
        // this.categories = this.categories;
        // this.authors = this.authors;
        this.available = available;
        this.countOfCopies = countOfCopies;
    }

    protected NewBookDTO(final int countOfCopies) {
        super();
        this.countOfCopies = countOfCopies;
    }

    public void setCountOfCopies(final int countOfCopies) {
        this.countOfCopies = countOfCopies;
    }

    public int getCountOfCopies() {
        return this.countOfCopies;
    }

}
