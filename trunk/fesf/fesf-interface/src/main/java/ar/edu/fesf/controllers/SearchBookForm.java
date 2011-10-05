package ar.edu.fesf.controllers;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.SearchBook;

public class SearchBookForm extends Form<Book> {

    private static final long serialVersionUID = 9029842939503423488L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public SearchBookForm(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    private void initialize() {
        this.add(new TextField<String>("title"));
        this.add(new Button("save"));
    }

    @Override
    protected void onSubmit() {
        this.setResponsePage(new SearchBook(this.getBookService().findLikeProperty("title",
                this.getRequest().getParameter("title")), this.getModelObject()));
    }
}
