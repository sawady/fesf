package ar.edu.fesf.view.pages.librarian;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import ar.edu.fesf.controllers.AjaxNamedAction;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.view.pages.books.BookSearchResultPanel;
import ar.edu.fesf.view.pages.generic.ActionsPanel;

public class LibrarianDataTablePanel extends BookSearchResultPanel {

    private static final long serialVersionUID = 1L;

    public LibrarianDataTablePanel(final String id, final List<Book> list, final IAjaxCallback<Book> callback,
            final IAjaxCallback<Book> newBookCallback) {
        super(id, list, callback);
        this.add(new AjaxFallbackLink<Object>("newBook") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                newBookCallback.apply(target, new Book());
            }

        });
    }

    @Override
    public Panel actionsPanel(final String componentId, final IModel<Book> rowModel) {
        List<AjaxNamedAction<Book>> actions = new ArrayList<AjaxNamedAction<Book>>();
        actions.add(new AjaxNamedAction<Book>("edit", this.getCallback()));
        return new ActionsPanel<Book>(componentId, rowModel.getObject(), actions);
    }

}
