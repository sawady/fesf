package ar.edu.fesf.controllers;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
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

    public BookForm(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    // TODO completar con todos los campos
    private void initialize() {

        // FeedbackPanel
        final FeedbackPanel feedback = new FeedbackPanel("feedbackPanel");
        feedback.setOutputMarkupId(true);
        this.add(feedback);

        // filteredErrorLevels will not be shown in the FeedbackPanel
        int[] filteredErrorLevels = new int[] { FeedbackMessage.ERROR };
        feedback.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));

        // Textfield for entering a name
        TextField<String> titleField = new TextField<String>("title", new PropertyModel<String>(this.getModelObject(),
                "title"));
        titleField.setOutputMarkupId(true);

        // This shows feedback when the name input is not correct.
        final FeedbackLabel titleFeedbackLabel = new FeedbackLabel("title_feedback", titleField);
        titleFeedbackLabel.setOutputMarkupId(true);
        this.add(titleFeedbackLabel);

        titleField.add(new ComponentVisualErrorBehavior("onblur", titleFeedbackLabel));
        this.add(titleField);

        Label titleLabel = new Label("title.label", "Title");
        this.add(titleLabel);

        this.add(new Button("save"));
    }

    @Override
    public void onSubmit() {
        this.getBookService().save(this.getModelObject());
        // this.setResponsePage(new BookSearchResultPanel(this.getBookService().findAll()));
    }

}
