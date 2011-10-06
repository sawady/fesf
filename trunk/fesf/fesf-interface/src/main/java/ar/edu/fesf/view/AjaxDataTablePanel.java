package ar.edu.fesf.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.fesf.controllers.GenericSortableDataProvider;

public class AjaxDataTablePanel<T extends Serializable> extends Panel {

    private static final long serialVersionUID = 9141385162784081359L;

    /**
     * Constructor.
     */
    public AjaxDataTablePanel(final String id, final List<T> list, final String sortField, final List<String> columnNames) {
        super(id);
        List<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (String name : columnNames) {
            columns.add(new PropertyColumn<T>(new Model<String>(name), name, name));
        }
        this.add(new AjaxFallbackDefaultDataTable<T>("table", columns, new GenericSortableDataProvider<T>(list,
                sortField), 5));
    }
}
