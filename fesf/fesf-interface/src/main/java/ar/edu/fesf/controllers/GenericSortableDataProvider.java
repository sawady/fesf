package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.services.GenericTranstactionalRepository;

/**
 * TODO: description
 */
public class GenericSortableDataProvider<T extends Serializable> extends SortableDataProvider<T> {

    private static final long serialVersionUID = 3674030688602763779L;

    public GenericTranstactionalRepository<T> getTransactionRepository() {
        return this.transactionRepository;
    }

    public void setTransactionRepository(final GenericTranstactionalRepository<T> transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private GenericTranstactionalRepository<T> transactionRepository;

    public GenericSortableDataProvider(final GenericTranstactionalRepository<T> service, final String propertyToSort) {
        super();
        this.transactionRepository = service;
        this.setSort(propertyToSort, true);
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        return this.getTransactionRepository().getIterator();
    }

    @Override
    public int size() {
        return this.getTransactionRepository().count();
    }

    @Override
    public IModel<T> model(final T object) {
        return new Model<T>(object);
    }

}
