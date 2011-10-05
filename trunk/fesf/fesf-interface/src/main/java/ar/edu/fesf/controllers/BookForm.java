package ar.edu.fesf.controllers;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.view.SearchBook;

public class BookForm extends Form<Book> {

    private static final long serialVersionUID = -2681835593630799248L;

    @SpringBean(name = "service.book")
    private BookService bookService;

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookForm(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    // TODO completar con todos los campos
    private void initialize() {
        this.add(new TextField<String>("title"));
        this.add(new Button("save"));
    }

    @Override
    public void onSubmit() {
        this.getBookService().save(this.getModelObject());
        this.setResponsePage(new SearchBook(this.getBookService().findAll()));
    }

}
