package ar.edu.fesf.controllers;

import java.util.ArrayList;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.BookSearchResultPanel;

public class BookSearchForm extends Form<Book> {

    private static final long serialVersionUID = 9029842939503423488L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookSearchForm(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    private void initialize() {
        this.add(new TextField<String>("title"));
        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                this.getParent().getParent().getParent()
                        .replaceWith(new BookSearchResultPanel("contentPanel", new ArrayList<Book>()));
            }
        });
        // this.add(new Button("submit"));
    }

    @Override
    protected void onSubmit() {
        // this.setResponsePage(new Home(new BookSearchResultPanel("contentPanel", BookSearchForm.this.getBookService()
        // .findLikeProperty("title", this.getRequest().getParameter("title")))));
    }
}
