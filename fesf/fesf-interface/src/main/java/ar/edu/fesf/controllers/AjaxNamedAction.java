package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxNamedAction<T> implements IAjaxCallback<T> {

    private static final long serialVersionUID = 1L;

    private String name;

    private IAjaxCallback<T> ajaxCallback;

    public AjaxNamedAction(final String name, final IAjaxCallback<T> ajaxCallback) {
        super();
        this.name = name;
        this.ajaxCallback = ajaxCallback;
    }

    @Override
    public void apply(final AjaxRequestTarget target, final T object) {
        this.ajaxCallback.apply(target, object);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public IAjaxCallback<T> getAjaxCallback() {
        return this.ajaxCallback;
    }

    public void setAjaxCallback(final IAjaxCallback<T> ajaxCallback) {
        this.ajaxCallback = ajaxCallback;
    }

}
