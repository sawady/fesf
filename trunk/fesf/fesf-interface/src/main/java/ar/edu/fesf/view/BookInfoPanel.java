package ar.edu.fesf.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.fesf.model.Book;

public class BookInfoPanel extends Panel {

    private static final long serialVersionUID = -640542220956725256L;

    public BookInfoPanel(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    private void initialize() {
        this.add(new Label("title"));
    }

}
