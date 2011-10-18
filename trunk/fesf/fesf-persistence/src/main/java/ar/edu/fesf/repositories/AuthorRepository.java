package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Author;

public class AuthorRepository extends HibernateGenericDAO<Author> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Author> getDomainClass() {
        return Author.class;
    }

}
