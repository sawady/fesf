package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public interface ServiceToForm<T> {

    T getObject();

    IAjaxCallback<T> doSubmitCallback(AjaxRequestTarget target, Form<T> form);

    Panel getFieldsPanel(String id);

}
