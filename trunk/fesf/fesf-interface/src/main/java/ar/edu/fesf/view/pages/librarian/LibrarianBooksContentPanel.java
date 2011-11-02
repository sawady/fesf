package ar.edu.fesf.view.pages.librarian;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.dtos.EditBookDTO;
import ar.edu.fesf.services.dtos.NewBookDTO;
import ar.edu.fesf.view.pages.books.BookEditFormFieldsPanel;
import ar.edu.fesf.view.pages.books.BookNewFormFieldsPanel;
import ar.edu.fesf.view.pages.generic.GenericFormPanel;

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
        this.setLibrarianDataTablePanel(new LibrarianDataTablePanel("content", new ArrayList<Book>(), this
                .changeToEditBookPanel(), this.changeToNewBookPanel()));
        this.getLibrarianDataTablePanel().setOutputMarkupId(true);
        this.add(this.getLibrarianDataTablePanel());
    }

    public IAjaxCallback<Book> changeToNewBookPanel() {
        return new AjaxReplacePanel<Book>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Book book) {
                return LibrarianBooksContentPanel.this.getNewBookFormPanel(new NewBookDTO());
            }

        };
    }

    public IAjaxCallback<Book> changeToEditBookPanel() {
        return new AjaxReplacePanel<Book>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Book book) {
                return LibrarianBooksContentPanel.this.getEditBookFormPanel(new EditBookDTO(
                        LibrarianBooksContentPanel.this.getBookService().initializeBookInfo(book)));
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

    private IAjaxCallback<Book> showPredefinedBooks() {
        return new IAjaxCallback<Book>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Book object) {
                LibrarianBooksContentPanel.this.changeBookTablePanel().callback(target, new ArrayList<Book>());
            }
        };
    }

    public GenericFormPanel<NewBookDTO> getNewBookFormPanel(final NewBookDTO bookDTO) {
        GenericFormPanel<NewBookDTO> bookFormPanel = new GenericFormPanel<NewBookDTO>("content") {

            private static final long serialVersionUID = 1L;

            @Override
            public PanelServiceToForm<NewBookDTO> getFieldsPanel(final String id) {
                return new BookNewFormFieldsPanel(id, bookDTO, LibrarianBooksContentPanel.this.showPredefinedBooks());
            }

        };
        bookFormPanel.setOutputMarkupId(true);
        return bookFormPanel;
    }

    public GenericFormPanel<EditBookDTO> getEditBookFormPanel(final EditBookDTO bookDTO) {
        GenericFormPanel<EditBookDTO> bookFormPanel = new GenericFormPanel<EditBookDTO>("content") {

            private static final long serialVersionUID = 1L;

            @Override
            public PanelServiceToForm<EditBookDTO> getFieldsPanel(final String id) {
                return new BookEditFormFieldsPanel(id, bookDTO, LibrarianBooksContentPanel.this.showPredefinedBooks());
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
