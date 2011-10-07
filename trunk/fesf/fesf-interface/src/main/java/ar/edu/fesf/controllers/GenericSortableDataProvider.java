package ar.edu.fesf.controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class GenericSortableDataProvider<T extends Serializable> extends SortableDataProvider<T> {

    private static final long serialVersionUID = 3674030688602763779L;

    private List<T> list;

    public GenericSortableDataProvider(final List<T> list, final String propertyToSort) {
        super();
        this.setList(list);
        this.setSort(propertyToSort, true);
    }

    @Override
    public Iterator<? extends T> iterator(final int first, final int count) {
        List<T> subList = this.getList().subList(first, first + count);
        return subList.iterator();
    }

    @Override
    public int size() {
        return this.getList().size();
    }

    @Override
    public IModel<T> model(final T object) {
        return new CompoundPropertyModel<T>(object);
    }

    private void setList(final List<T> list) {
        this.list = list;
    }

    private List<T> getList() {
        return this.list;
    }

}
