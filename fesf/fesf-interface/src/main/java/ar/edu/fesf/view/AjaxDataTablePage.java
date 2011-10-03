package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.GenericSortableDataProvider;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class AjaxDataTablePage extends BasePage {

    @SpringBean(name = "service.book")
    private BookService bookService;

    /**
     * Constructor.
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
    public AjaxDataTablePage() {
        super();
        List columns = new ArrayList();

        columns.add(new AbstractColumn(new Model("Actions")) {
            @Override
            public void populateItem(final Item cellItem, final String componentId, final IModel model) {
                cellItem.add(new ActionPanel(componentId, model));
            }
        });

        columns.add(new PropertyColumn<String>(new Model("Title"), "title", "title"));

        this.add(new AjaxFallbackDefaultDataTable("table", columns, new GenericSortableDataProvider<Book>(
                this.bookService, "title"), 8));
    }
}
