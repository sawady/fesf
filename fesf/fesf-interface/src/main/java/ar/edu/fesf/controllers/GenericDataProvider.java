package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: description
 */
public class GenericDataProvider<T extends Serializable> implements IDataProvider<T> {

    private static final long serialVersionUID = 1L;

    private List<T> list;

    @Override
    public void detach() {
        // shit
    }

    public GenericDataProvider(final List<T> list) {
        this.setList(list);
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        return this.getList().subList(first, first + count).iterator();
    }

    @Override
    public int size() {
        return this.getList().size();
    }

    @Override
    public IModel<T> model(final T object) {
        return new Model<T>(object);
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return this.list;
    }

}
