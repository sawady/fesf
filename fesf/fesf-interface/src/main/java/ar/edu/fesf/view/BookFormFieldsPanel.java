package ar.edu.fesf.view;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;

public class BookFormFieldsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public BookFormFieldsPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        this.add(new RequiredTextField<String>("title"));
    }

}
