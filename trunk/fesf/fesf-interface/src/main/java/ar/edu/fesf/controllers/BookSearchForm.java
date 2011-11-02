package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.pages.books.BookSearchPanel;

public class BookSearchForm extends Form<BookSearchForm> {

    private static final long serialVersionUID = 9029842939503423488L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    private BookSearchPanel searchPanel;

    private String input;

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
        super(id);
        this.searchPanel = searchPanel;
        this.initialize();
    }

    private void initialize() {

        // this.add(new Label("title.label", new Model<String>("Title")));
        this.add(new TextField<String>("input", new PropertyModel<String>(this, "input")));
        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                if (BookSearchForm.this.input != null) {
                    BookSearchForm.this.getSearchPanel().receiveResult(target,
                            BookSearchForm.this.getBookService().bookSearch(BookSearchForm.this.input));
                }
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                // TODO completar onError
            }

        });
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(final String input) {
        this.input = input;
    }

}
