package ar.edu.fesf.controllers;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

public interface IAjaxCallback<T> extends Serializable {

    void apply(AjaxRequestTarget target, T object);

}
