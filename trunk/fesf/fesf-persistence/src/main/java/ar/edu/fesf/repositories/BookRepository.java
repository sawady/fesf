package ar.edu.fesf.repositories;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.edu.fesf.model.Book;

public class BookRepository extends HibernateGenericDAO<Book> {

    private static final long serialVersionUID = 1020054408532092880L;

    @Override
    protected Class<Book> getDomainClass() {
        return Book.class;
    }

    public List<Book> bookSearch(final String input) {
        return this.findBy(Restrictions.ilike("title", "%" + input + "%"), Order.desc("countOfLouns"));
    }

    public List<Book> getTop20() {
        return this.findBy(Restrictions.isNotNull("title"), Order.desc("countOfLouns"), 20);
    }

    public List<Book> getRecentlyAvailable(final int countOfResults) {
        return this.findBy(Restrictions.isNotNull("acquisitionDate"), Order.desc("acquisitionDate"), countOfResults);
    }

}
