package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Publisher;

public class PublisherRepository extends HibernateGenericDAO<Publisher> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Publisher> getDomainClass() {
        return Publisher.class;
    }

}
