package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.services.GenericTranstactionalRepository;

/**
 * TODO: description
 */
public class GenericProvider<T extends Serializable> implements IDataProvider<T> {

    private static final long serialVersionUID = -740534197547524703L;

    private GenericTranstactionalRepository<T> transactionRepository;

    public GenericProvider(final GenericTranstactionalRepository<T> service) {
        this.transactionRepository = service;
    }

    public GenericTranstactionalRepository<T> getTransactionRepository() {
        return this.transactionRepository;
    }

    public void setTransactionRepository(final GenericTranstactionalRepository<T> transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void detach() {
        // TODO preguntar
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
