package ar.edu.fesf.repositories;

import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;

public class BookRepository extends HibernateGenericDAO<Book> {

    private static final long serialVersionUID = 1020054408532092880L;

    @Override
    protected Class<Book> getDomainClass() {
        return Book.class;
    }

    @SuppressWarnings("unchecked")
    public List<Book> bookSearch(final String input, final boolean onlyAvailables) {

        String onlyAvailablesString = "";
        if (onlyAvailables) {
            onlyAvailablesString = " and book.available = true ";
        }

        String toLowerCaseInput = input.toLowerCase(Locale.getDefault());
        return this
                .getHibernateTemplate()
                .find("select distinct book from "
                        + this.persistentClass.getName()
                        + " book left join book.authors author left join book.categories category where (lower(book.title) like '%"
                        + toLowerCaseInput + "%' or lower(author.name) like '%" + toLowerCaseInput
                        + "%' or lower(category.name) like '%" + toLowerCaseInput + "%')" + onlyAvailablesString
                        + " order by book.countOfLouns desc");
    }

    @SuppressWarnings("unchecked")
    public List<Book> booksBorrowedByThoseWhoBorrowed(final int bookID, final int maxResults) {
        Query query = this.getSession().createQuery(
                "select distinct(loanedBook) from " + this.persistentClass.getName()
                        + " book join book.loanees loanee join loanee.loanedBooks loanedBook where book.id = " + bookID
                        + " and loanedBook.id != " + bookID + " order by loanedBook.countOfLouns desc");

        query.setMaxResults(maxResults);

        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Book> findByCategory(final Category category) {
        Query query = this.getSession().createQuery(
                "select distinct(book) from " + this.persistentClass.getName()
                        + " book join book.categories category where category.name = '" + category.getName()
                        + "' order by book.countOfLouns desc");

        query.setMaxResults(50);
        return query.list();
    }

    public List<Book> getTop20() {
        return this.findBy(Restrictions.isNotNull("title"), Order.desc("countOfLouns"), 20);
    }

    public List<Book> getRecentlyAvailable(final int countOfResults) {
        return this.findBy(Restrictions.isNotNull("acquisitionDate"), Order.desc("acquisitionDate"), countOfResults);
    }

}
