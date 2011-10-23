package ar.edu.fesf.view;

import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MinimumValidator;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.PublisherService;
import ar.edu.fesf.services.dtos.NewBookDTO;

public class BookNewFormFieldsPanel extends PanelServiceToForm<NewBookDTO> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BookService bookService;

    @SpringBean
    private PublisherService publisherService;

    private IAjaxCallback<Book> ajaxCallback;

    private NewBookDTO book;

    public BookNewFormFieldsPanel(final String id, final NewBookDTO bookDTO, final IAjaxCallback<Book> ajaxCallback) {
        super(id);
        this.book = bookDTO;
        this.ajaxCallback = ajaxCallback;
        this.initialize();
    }

    private void initialize() {
        this.add(new RequiredTextField<String>("title"));
        this.add(new RequiredTextField<String>("isbn"));
        this.add(new RequiredTextField<Integer>("countOfCopies", Integer.class).add(new MinimumValidator<Integer>(1)));
        this.add(new TextArea<String>("description"));
        this.add(new AutoCompleteTextField<String>("publisher", String.class) {

            private static final long serialVersionUID = 1L;

            @Override
            protected Iterator<String> getChoices(final String input) {
                return BookNewFormFieldsPanel.this.getPublisherService().getPublishersNamedLike(input);
            }

        }.setRequired(true));
    }

    @Override
    public NewBookDTO getObject() {
        return this.getBook();
    }

    @Override
    public void doSubmit(final AjaxRequestTarget target, final Form<NewBookDTO> form) {
        this.getBookService().registerNewBookDTO(this.getBook());
        this.getAjaxCallback().callback(target, null);
    }

    /* Accessors */

    public void setBook(final NewBookDTO book) {
        this.book = book;
    }

    public NewBookDTO getBook() {
        return this.book;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setAjaxCallback(final IAjaxCallback<Book> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

    public IAjaxCallback<Book> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setPublisherService(final PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public PublisherService getPublisherService() {
        return this.publisherService;
    }

}
