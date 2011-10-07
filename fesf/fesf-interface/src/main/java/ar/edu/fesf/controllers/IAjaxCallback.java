package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;

public interface IAjaxCallback<T> {

    void callback(AjaxRequestTarget target, T object);

}
