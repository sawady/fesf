package ar.edu.fesf.view.pages.books;

import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Author;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.model.Nameable;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.PersonService;
import ar.edu.fesf.wicket.application.SecuritySession;

public class BookInfoPanel extends Panel {

    @SpringBean(name = "service.book")
    private BookService bookService;

    @SpringBean
    private PersonService personService;

    private static final long serialVersionUID = -640542220956725256L;

    public BookInfoPanel(final String id, final Book book, final IAjaxCallback<Book> callback,
            final IAjaxCallback<Book> relatedBookCallback, final IAjaxCallback<Person> loaneeCallback) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize(callback, relatedBookCallback, book, loaneeCallback);
    }

    // TODO faltan un par de campos
    private void initialize(final IAjaxCallback<Book> callback, final IAjaxCallback<Book> relatedBookCallback,
            final Book book, final IAjaxCallback<Person> loaneeCallback) {

        this.add(new Label("title"));
        this.add(new Label("authorNames", this.concatenate(book.getAuthors())));
        this.add(new Label("publisher.name"));
        this.add(new Label("categoryNames", this.concatenate(book.getCategories())));
        this.add(new Label("description"));
        this.add(new Label("isbn.value"));
        this.add(new Label("countOfAvailableCopies", book.getCountOfAvailableCopies().toString()));

        Person person = ((SecuritySession) this.getSession()).getPerson();
        if (person == null) {
            AjaxFallbackLink<String> ajaxFallbackLink = new AjaxFallbackLink<String>("borrowIt",
                    new Model<String>(null)) {

                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(final AjaxRequestTarget target) {
                    // No hago ada
                }

            };
            this.add(ajaxFallbackLink);
            ajaxFallbackLink.add(new Label("borrowText", ""));
        } else {
            final Person personDB = this.personService.initializeLoaneeInfo(person);
            if (personDB.cannotHaveMoreLoans()) {
                AjaxFallbackLink<String> ajaxFallbackLink = new AjaxFallbackLink<String>("borrowIt", new Model<String>(
                        null)) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        loaneeCallback.callback(target, personDB);
                    }

                };
                this.add(ajaxFallbackLink);
                ajaxFallbackLink.add(new Label("borrowText", "Return a book"));
            } else {
                AjaxFallbackLink<String> ajaxFallbackLink = new AjaxFallbackLink<String>("borrowIt", new Model<String>(
                        null)) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        callback.callback(target, book);
                    }

                };
                this.add(ajaxFallbackLink);
                ajaxFallbackLink.add(new Label("borrowText", "Borrow It!"));
            }
        }

        List<Book> relatedBooks = this.getBookService().relatedBooks(book.getId(), 10);
        this.add(new HorizontalBookPanel("relatedBooks", relatedBooks, relatedBookCallback));
    }

    // Gracias java por no permitirme abstraer esto
    private String concatenate(final Set<Category> list) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Nameable nameable : list) {
            stringbuf.append(separator).append(nameable.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }

    private String concatenate(final List<Author> list) {
        String separator = "";
        StringBuffer stringbuf = new StringBuffer();
        for (Nameable nameable : list) {
            stringbuf.append(separator).append(nameable.getName());
            if ("".equals(separator)) {
                separator = ", ";
            }
        }
        return stringbuf.toString();
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
