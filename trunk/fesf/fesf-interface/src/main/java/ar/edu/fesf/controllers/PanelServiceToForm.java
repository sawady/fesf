package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class PanelServiceToForm<T> extends Panel {

    private static final long serialVersionUID = 1L;

    public PanelServiceToForm(final String id) {
        super(id);
    }

    public abstract T getObject();

    public abstract void doSubmit(AjaxRequestTarget target, Form<T> form);

}
