package ar.edu.fesf.view.pages.generic;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.GenericForm;
import ar.edu.fesf.controllers.PanelServiceToForm;

public abstract class GenericFormPanel<T> extends Panel {

    private static final long serialVersionUID = 1L;

    public GenericFormPanel(final String id) {
        super(id);
        this.add(new GenericForm<T>("form", this.getFieldsPanel("formFieldPanel")));
    }

    public abstract PanelServiceToForm<T> getFieldsPanel(String id);

}
