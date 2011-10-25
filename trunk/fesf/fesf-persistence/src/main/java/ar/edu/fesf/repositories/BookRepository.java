package ar.edu.fesf.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.edu.fesf.model.Book;

public class BookRepository extends HibernateGenericDAO<Book> {

    private static final long serialVersionUID = 1020054408532092880L;

    @Override
    protected Class<Book> getDomainClass() {
        return Book.class;
    }

    @SuppressWarnings("unchecked")
    public List<Book> bookSearch(final String input) {
        String goodInput = input.toLowerCase();
        return this
                .getHibernateTemplate()
                .find("select distinct book from "
                        + this.persistentClass.getName()
                        + " book join book.authors author join book.categories category where lower(book.title) like '%"
                        + goodInput + "%' or lower(author.name) like '%" + goodInput
                        + "%' or lower(category.name) like '%" + goodInput + "%' order by book.countOfLouns desc");
    }

    @SuppressWarnings("unchecked")
    public List<Book> booksBorrowedByThoseWhoBorrowed(final int bookID, final int maxResults) {
        Query q = this.getSession().createQuery(
                "select distinct(loanedBook) from " + this.persistentClass.getName()
                        + " book join book.loanees loanee join loanee.loanedBooks loanedBook where book.id = " + bookID
                        + " and loanedBook.id != " + bookID + " order by loanedBook.countOfLouns desc");

        q.setMaxResults(maxResults);

        return q.list();
    }

    public List<Book> getTop20() {
        return this.findBy(Restrictions.isNotNull("title"), Order.desc("countOfLouns"), 20);
    }

    public List<Book> getRecentlyAvailable(final int countOfResults) {
        return this.findBy(Restrictions.isNotNull("acquisitionDate"), Order.desc("acquisitionDate"), countOfResults);
    }

}
