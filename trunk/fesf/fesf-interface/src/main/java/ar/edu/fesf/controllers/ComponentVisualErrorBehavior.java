package ar.edu.fesf.controllers;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;

/**
 * Behavior that checks if a {@link FormComponent} is valid. Valid {@link FormComponent} objects get the CSS class
 * 'formcomponent valid' and invalid {@link FormComponent} objects get the CSS class 'formcomponent invalid'.
 * 
 * See {@link AjaxFormComponentUpdatingBehavior} for more details over the parent class.
 * 
 * You can use this code under Apache 2.0 license, as long as you retain the copyright messages.
 * 
 * Tested with Wicket 1.3.4
 * 
 * @author Daan, StuQ.nl
 */
public class ComponentVisualErrorBehavior extends AjaxFormComponentUpdatingBehavior {

    private static final long serialVersionUID = 1L;

    /** Field updateComponent holds the component that must be updated when validation is done. */
    private Component updateComponent = null;

    /**
     * Constructor.
     * 
     * @param event
     *            of type {@link String} (for example 'onblur', 'onkeyup', etc.)
     * @param updateComponent
     *            is the {@link Component} that must be updated (for example the {@link FeedbackLabel} containing the
     *            error message for this {@link FormComponent})
     */
    public ComponentVisualErrorBehavior(final String event, final Component updateComponent) {
        super(event);
        this.setUpdateComponent(updateComponent);
    }

    /**
     * Listener invoked on the ajax request. This listener is invoked after the {@link Component}'s model has been
     * updated. Handles the change of a css class when an error has occurred.
     * 
     * @param ajaxRequestTarget
     *            of type AjaxRequestTarget
     * @param error
     *            of type RuntimeException
     */
    @Override
    protected void onError(final AjaxRequestTarget ajaxRequestTarget, final RuntimeException error) {
        this.changeCssClass(ajaxRequestTarget, false, "invalid");
    }

    /**
     * Listener invoked on the ajax request. This listener is invoked after the {@link Component}'s model has been
     * updated. Handles the change of a css class when validation was succesful.
     * 
     * @param ajaxRequestTarget
     *            of type AjaxRequestTarget
     */
    @Override
    protected void onUpdate(final AjaxRequestTarget ajaxRequestTarget) {
        this.changeCssClass(ajaxRequestTarget, true, "valid");
    }

    /**
     * Changes the CSS class of the linked {@link FormComponent} via AJAX.
     * 
     * @param ajaxRequestTarget
     *            of type AjaxRequestTarget
     * @param valid
     *            Was the validation succesful?
     * @param cssClass
     *            The CSS class that must be set on the linked {@link FormComponent}
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void changeCssClass(final AjaxRequestTarget ajaxRequestTarget, final boolean valid, final String cssClass) {
        FormComponent<?> formComponent = this.getFormComponent();

        if (formComponent.isValid() == valid) {
            formComponent.add(new AttributeModifier("class", true, new Model("formcomponent " + cssClass)));
            ajaxRequestTarget.addComponent(formComponent);
        }

        if (this.getUpdateComponent() != null) {
            ajaxRequestTarget.addComponent(this.getUpdateComponent());
        }
    }

    private void setUpdateComponent(final Component updateComponent) {
        this.updateComponent = updateComponent;
    }

    private Component getUpdateComponent() {
        return this.updateComponent;
    }
}
