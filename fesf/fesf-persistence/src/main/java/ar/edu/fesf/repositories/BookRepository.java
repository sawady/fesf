package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Book;

public class BookRepository extends HibernateGenericDAO<Book> {

    private static final long serialVersionUID = 1020054408532092880L;

    @Override
    protected Class<Book> getDomainClass() {
        return Book.class;
    }

}
