package ar.edu.fesf.repositories;

import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;

import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;

public class LoanRepository extends HibernateGenericDAO<Loan> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Loan> getDomainClass() {
        return Loan.class;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findBastardLoanees() {
        Query query = this.getSession().createQuery(
                "select distinct loanee from " + this.persistentClass.getName()
                        + " loan join loan.person loanee where " + "loan.agreedReturnDate < :today_date "
                        + "and loan.returnDate is null" + " order by loanee.surname desc");

        // Esto tambien funcionaria (not :today_date between loan.date and loan.agreedReturnDate)
        query.setParameter("today_date", new DateTime());
        return query.list();
    }

}
