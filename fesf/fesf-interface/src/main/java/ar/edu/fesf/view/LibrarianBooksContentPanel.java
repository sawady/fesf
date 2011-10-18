package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

public class LibrarianBooksContentPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private LibrarianDataTablePanel librarianDataTablePanel;

    public LibrarianBooksContentPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.setLibrarianDataTablePanel(new LibrarianDataTablePanel("content", this.getBookService().findAll(), this
                .changeToEditBookPanel()));
        this.getLibrarianDataTablePanel().setOutputMarkupId(true);
        this.add(this.getLibrarianDataTablePanel());
    }

    public IAjaxCallback<Book> changeToEditBookPanel() {
        return new AjaxReplacePanel<Book>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Book book) {
                return LibrarianBooksContentPanel.this.getEditBookFormPanel(book);
            }

        };
    }

    public IAjaxCallback<List<Book>> changeBookTablePanel() {
        return new AjaxReplacePanel<List<Book>>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final List<Book> list) {
                LibrarianBooksContentPanel.this.getLibrarianDataTablePanel().replaceTable(target, list);
                return LibrarianBooksContentPanel.this.getLibrarianDataTablePanel();
            }

        };
    }

    private IAjaxCallback<Book> showAllResults() {
        return new IAjaxCallback<Book>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Book book) {
                LibrarianBooksContentPanel.this.changeBookTablePanel().callback(target,
                        LibrarianBooksContentPanel.this.getBookService().findAll());
            }
        };
    }

    public GenericFormPanel<Book> getEditBookFormPanel(final Book book) {
        GenericFormPanel<Book> bookFormPanel = new GenericFormPanel<Book>("content") {

            private static final long serialVersionUID = 1L;

            @Override
            public PanelServiceToForm<Book> getFieldsPanel(final String id) {
                return new BookFormFieldsPanel(id, book, LibrarianBooksContentPanel.this.showAllResults());
            }

        };
        bookFormPanel.setOutputMarkupId(true);
        return bookFormPanel;
    }

    /*** ACCESSORS ***/

    public void setLibrarianDataTablePanel(final LibrarianDataTablePanel librarianListedBooksPanel) {
        this.librarianDataTablePanel = librarianListedBooksPanel;
    }

    public LibrarianDataTablePanel getLibrarianDataTablePanel() {
        return this.librarianDataTablePanel;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }
}
