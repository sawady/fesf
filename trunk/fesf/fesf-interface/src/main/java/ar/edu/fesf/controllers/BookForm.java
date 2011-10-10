package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.services.BookService;

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

    public BookForm(final String id, final Book book, final IAjaxCallback<Form<Book>> iAjaxCallback) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize(iAjaxCallback);
    }

    // TODO completar con todos los campos
    private void initialize(final IAjaxCallback<Form<Book>> iAjaxCallback) {

        RequiredTextField<String> titleField = new RequiredTextField<String>("title");
        this.add(titleField);
        final FeedbackPanel titleFeedback = new FeedbackPanel("titleFeedback", new ComponentFeedbackMessageFilter(
                titleField));
        titleFeedback.setOutputMarkupId(true);
        this.add(titleFeedback);

        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(titleFeedback);
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                BookForm.this.getBookService().save((Book) form.getModelObject());
                iAjaxCallback.callback(target, (Form<Book>) form);
            }

        });
    }
}
