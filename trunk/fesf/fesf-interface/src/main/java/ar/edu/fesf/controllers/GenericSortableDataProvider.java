package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class GenericSortableDataProvider<T extends Serializable> extends SortableDataProvider<T> {

    private static final long serialVersionUID = 3674030688602763779L;

    List<T> list;

    public GenericSortableDataProvider(final List<T> list, final String propertyToSort) {
        super();
        this.list = list;
        this.setSort(propertyToSort, true);
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        return this.list.subList(first, first + count).iterator();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public IModel<T> model(final T object) {
        return new CompoundPropertyModel<T>(object);
    }

}
