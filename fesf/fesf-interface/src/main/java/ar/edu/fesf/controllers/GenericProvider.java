package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class GenericProvider<T extends Serializable> implements IDataProvider<T> {

    private static final long serialVersionUID = -740534197547524703L;

    List<T> list;

    public GenericProvider(final List<T> list) {
        super();
        this.list = list;
    }

    @Override
    public void detach() {
        // TODO preguntar
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        return this.getList().iterator();
    }

    @Override
    public int size() {
        return this.getList().size();
    }

    @Override
    public IModel<T> model(final T object) {
        return new Model<T>(object);
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

}
