package ar.edu.fesf.repositories;

import java.io.Serializable;

import ar.edu.fesf.model.Category;

public class CategoryRepository extends HibernateGenericDAO<Category> implements Serializable {

    private static final long serialVersionUID = -9050647951886400049L;

    @Override
    protected Class<Category> getDomainClass() {
        return Category.class;
    }

}
