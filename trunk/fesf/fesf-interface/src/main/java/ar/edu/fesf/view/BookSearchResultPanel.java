package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class BookSearchResultPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private AjaxDataTablePanel<Book> ajaxDataTablePanel;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookSearchResultPanel(final String id) {
        this(id, new ArrayList<Book>());
    }

    public BookSearchResultPanel(final String id, final List<Book> initialBooks) {
        super(id);
        this.initialize(initialBooks);
    }

    private void initialize(final List<Book> initialBooks) {
        this.setAjaxDataTablePanel(new AjaxDataTablePanel<Book>("table", initialBooks, this.getBookService()
                .getFieldForSort(), this.getBookService().getFieldNames()));
        this.getAjaxDataTablePanel().setOutputMarkupId(true);
        this.add(this.getAjaxDataTablePanel());
    }

    public void replaceTable(final AjaxRequestTarget target, final List<Book> books) {
        this.getAjaxDataTablePanel().replaceTable(target, books);
    }

    private void setAjaxDataTablePanel(final AjaxDataTablePanel<Book> ajaxDataTablePanel) {
        this.ajaxDataTablePanel = ajaxDataTablePanel;
    }

    private AjaxDataTablePanel<Book> getAjaxDataTablePanel() {
        return this.ajaxDataTablePanel;
    }
}
