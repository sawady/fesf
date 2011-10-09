package ar.edu.fesf.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.controllers.GenericSortableDataProvider;

public class AjaxDataTablePanel<T extends Serializable> extends Panel {

    private static final long serialVersionUID = 9141385162784081359L;

    private List<IColumn<T>> columns;

    private String sortField;

    /**
     * Constructor.
     */
    public AjaxDataTablePanel(final String id, final List<T> list, final String sortField,
            final List<String> columnNames) {
        super(id);
        this.setSortField(sortField);
        this.initalize(list, columnNames);
    }

    private void initalize(final List<T> list, final List<String> columnNames) {

        this.setColumns(new ArrayList<IColumn<T>>());

        for (String name : columnNames) {
            this.getColumns().add(new PropertyColumn<T>(new Model<String>(name), name, name));
        }

        this.add(new AjaxFallbackDefaultDataTable<T>("table", this.getColumns(), new GenericSortableDataProvider<T>(
                list, this.getSortField()), 5));
    }

    public void replaceTable(final AjaxRequestTarget target, final List<T> list) {
        this.replace(new AjaxFallbackDefaultDataTable<T>("table", this.getColumns(),
                new GenericSortableDataProvider<T>(list, this.getSortField()), 5));
        target.addComponent(this);
    }

    private void setColumns(final List<IColumn<T>> columns) {
        this.columns = columns;
    }

    private List<IColumn<T>> getColumns() {
        return this.columns;
    }

    private void setSortField(final String sortField) {
        this.sortField = sortField;
    }

    private String getSortField() {
        return this.sortField;
    }
}
