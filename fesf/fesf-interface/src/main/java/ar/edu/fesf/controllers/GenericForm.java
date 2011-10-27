package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class GenericForm<T> extends Form<T> {

    private static final long serialVersionUID = 1L;

    public GenericForm(final String id, final PanelServiceToForm<T> fieldsPanel) {
        super(id, new CompoundPropertyModel<T>(fieldsPanel.getObject()));
        this.initialize(fieldsPanel);
    }

    private void initialize(final PanelServiceToForm<T> fieldsPanel) {

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        this.add(feedbackPanel);

        this.add(fieldsPanel);

        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.add(feedbackPanel);
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                fieldsPanel.doSubmit(target, (Form<T>) form);
            }

        });
    }

}
