package ar.edu.fesf.view.pages.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.controllers.GenericSortableDataProvider;

public abstract class AjaxDataTablePanel<T extends Serializable> extends Panel {

    private static final long serialVersionUID = 9141385162784081359L;

    private List<IColumn<T>> columns;

    private int rowsPerPage = 5;

    /**
     * Constructor.
     */
    public AjaxDataTablePanel(final String id, final List<T> list) {
        super(id);
        this.initialize(list);
    }

    private void initialize(final List<T> list) {

        this.setColumns(new ArrayList<IColumn<T>>());

        this.getColumns().add(new AbstractColumn<T>(new Model<String>("Actions")) {

            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                    final IModel<T> rowModel) {
                cellItem.add(AjaxDataTablePanel.this.actionsPanel(componentId, rowModel));
            }

        });

        for (String name : this.getColumnNames()) {
            this.getColumns().add(new PropertyColumn<T>(new Model<String>(name), name, name));
        }

        this.add(new AjaxFallbackDefaultDataTable<T>("table", this.getColumns(), new GenericSortableDataProvider<T>(
                list, this.getSortFields()), this.getRowsPerPage()));
    }

    public abstract Panel actionsPanel(final String componentId, final IModel<T> rowModel);

    public abstract List<String> getColumnNames();

    public abstract List<String> getSortFields();

    public void replaceTable(final AjaxRequestTarget target, final List<T> list) {
        this.replace(new AjaxFallbackDefaultDataTable<T>("table", this.getColumns(),
                new GenericSortableDataProvider<T>(list, this.getSortFields()), this.getRowsPerPage()));
        target.add(this);
    }

    public void setColumns(final List<IColumn<T>> columns) {
        this.columns = columns;
    }

    public List<IColumn<T>> getColumns() {
        return this.columns;
    }

    public void setRowsPerPage(final int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getRowsPerPage() {
        return this.rowsPerPage;
    }

}
