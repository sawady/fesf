package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Loan;

public class LoanRepository extends HibernateGenericDAO<Loan> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Loan> getDomainClass() {
        return Loan.class;
    }

}
