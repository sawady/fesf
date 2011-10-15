package ar.edu.fesf.controllers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class GenericForm<T> extends Form<T> {

    private static final long serialVersionUID = 1L;

    private ServiceToForm<T> serviceToForm;

    public GenericForm(final String id, final ServiceToForm<T> serviceToForm) {
        super(id, new CompoundPropertyModel<T>(serviceToForm.getObject()));
        this.serviceToForm = serviceToForm;
        this.initialize();
    }

    private void initialize() {

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        this.add(feedbackPanel);

        this.add(this.getServiceToForm().getFieldsPanel("formFieldPanel"));

        this.add(new AjaxFallbackButton("submit", this) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(feedbackPanel);
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {

                GenericForm.this.getServiceToForm().doSubmitCallback(target, (Form<T>) form)
                        .callback(target, (T) this.getModelObject());
            }

        });
    }

    public void setServiceToForm(final ServiceToForm<T> serviceToForm) {
        this.serviceToForm = serviceToForm;
    }

    public ServiceToForm<T> getServiceToForm() {
        return this.serviceToForm;
    }

}
