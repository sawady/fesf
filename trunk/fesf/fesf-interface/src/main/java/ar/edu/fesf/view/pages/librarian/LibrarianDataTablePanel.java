package ar.edu.fesf.view.pages.librarian;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;

public class LibrarianDataTablePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LibrarianDataTablePanel(final String id, final List<Book> list, final IAjaxCallback<Book> callback,
            final IAjaxCallback<Book> newBookCallback) {
        super(id);
        // this.add(new AjaxFallbackLink<Object>("newBook") {
        //
        // private static final long serialVersionUID = 1L;
        //
        // @Override
        // public void onClick(final AjaxRequestTarget target) {
        // newBookCallback.apply(target, new Book());
        // }
        //
        // });
    }

    // @Override
    // public Panel actionsPanel(final String componentId, final IModel<Book> rowModel) {
    // List<AjaxNamedAction<Book>> actions = new ArrayList<AjaxNamedAction<Book>>();
    // actions.add(new AjaxNamedAction<Book>("edit", this.getCallback()));
    // return new ActionsPanel<Book>(componentId, rowModel.getObject(), actions);
    // }

}
