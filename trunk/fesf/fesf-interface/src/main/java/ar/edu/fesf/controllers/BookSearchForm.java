package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.BookSearchPanel;

public class BookSearchForm extends Form<Book> {

    private static final String TITLE = "title";

    private static final long serialVersionUID = 9029842939503423488L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private BookSearchPanel searchPanel;

    public BookSearchPanel getSearchPanel() {
        return this.searchPanel;
    }

    public void setSearchPanel(final BookSearchPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookSearchForm(final String id, final BookSearchPanel searchPanel) {
        super(id, new CompoundPropertyModel<Book>(new Book()));
        this.searchPanel = searchPanel;
        this.initialize();
    }

    private void initialize() {

        this.add(new TextField<String>(TITLE));
        this.add(new Label("title.label", new Model<String>("Title")));

        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                StringValue parameterTitle = this.getRequest().getQueryParameters().getParameterValue(TITLE);
                BookSearchForm.this.getSearchPanel().recieveResult(target,
                        BookSearchForm.this.getBookService().findLikeProperty(TITLE, parameterTitle.toString()));
            }

            @Override
            protected void onError(final AjaxRequestTarget arg0, final Form<?> arg1) {
                throw new UnsupportedOperationException();
            }

        });
    }
}