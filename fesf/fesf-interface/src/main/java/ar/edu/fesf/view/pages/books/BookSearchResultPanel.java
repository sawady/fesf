package ar.edu.fesf.view.pages.books;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxNamedAction;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.pages.generic.ActionsPanel;
import ar.edu.fesf.view.pages.generic.AjaxDataTablePanel;

public class BookSearchResultPanel extends AjaxDataTablePanel<Book> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private IAjaxCallback<Book> callback;

    public BookSearchResultPanel(final String id, final List<Book> list, final IAjaxCallback<Book> callback) {
        super(id, list);
        this.setCallback(callback);
    }

    @Override
    public Panel actionsPanel(final String componentId, final IModel<Book> rowModel) {
        List<AjaxNamedAction<Book>> actions = new ArrayList<AjaxNamedAction<Book>>();
        actions.add(new AjaxNamedAction<Book>("more", this.getCallback()));
        return new ActionsPanel<Book>(componentId, rowModel.getObject(), actions);
    }

    @Override
    public List<String> getColumnNames() {
        return this.getBookService().getFieldNames();
    }

    @Override
    public List<String> getSortFields() {
        return this.getBookService().getFieldForSort();
    }

    /* Accessors */
    public void setCallback(final IAjaxCallback<Book> callback) {
        this.callback = callback;
    }

    public IAjaxCallback<Book> getCallback() {
        return this.callback;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }
}
