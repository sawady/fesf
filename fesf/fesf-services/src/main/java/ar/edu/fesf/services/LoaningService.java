package ar.edu.fesf.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;

public class LoaningService extends GenericTransactionalRepositoryService<Loan> {

    private static final long serialVersionUID = 1L;

    @Transactional
    public void registerLoan(final Loan loan, final Book book) {
        try {
            loan.assignCopy(book.getAvailableCopy());
        } catch (RuntimeException e) {
            throw new NoAvailableBookCopyException(e.getMessage());
        }
        this.save(loan);
    }

    @Transactional
    public void registerBookReturn(final Loan loan) {
        loan.setFinished();
    }
}
