package ar.edu.fesf.view;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.ServiceToForm;

public class GenericFormPanel<T> extends Panel {

    private static final long serialVersionUID = 1L;

    public GenericFormPanel(final String id, final ServiceToForm<T> serviceToForm) {
        super(id);
        this.add(new GenericFormPanel<T>("form", serviceToForm));
    }

}
