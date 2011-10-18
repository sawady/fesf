package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class AjaxReplacePanel<T> implements IAjaxCallback<T> {

    private static final long serialVersionUID = 1L;

    private Panel parent;

    public AjaxReplacePanel(final Panel panel) {
        this.setParent(panel);
    }

    public abstract Panel getNewPanel(final AjaxRequestTarget target, final T object);

    @Override
    public void callback(final AjaxRequestTarget target, final T object) {
        Panel newPanel = this.getNewPanel(target, object);
        this.getParent().replace(newPanel);
        target.add(newPanel);
    }

    public void setParent(final Panel parent) {
        this.parent = parent;
    }

    public Panel getParent() {
        return this.parent;
    }

}
