package ar.edu.fesf.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
import ar.edu.fesf.services.BookService;

public class CategoriesSidebar extends Panel {

    @SpringBean(name = "service.book")
    private BookService bookService;

    private IAjaxCallback<List<Book>> callback;

    private static final long serialVersionUID = -4399991188117990545L;

    public CategoriesSidebar(final String id, final IAjaxCallback<List<Book>> callback) {
        super(id);
        this.callback = callback;
        this.initialize();
    }

    private void initialize() {

        this.add(new ListView<Category>("links", this.getBookService().findAllCategories()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Category> item) {

                AjaxFallbackLink<Category> link = new AjaxFallbackLink<Category>("link", item.getModel()) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        CategoriesSidebar.this.getCallback().callback(target,
                                CategoriesSidebar.this.getBookService().findByCategory(this.getModelObject()));
                    }

                };

                link.add(new Label("linkText", item.getModelObject().getName()));

                item.add(link);
            }
        });
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setCallback(final IAjaxCallback<List<Book>> callback) {
        this.callback = callback;
    }

    public IAjaxCallback<List<Book>> getCallback() {
        return this.callback;
    }
}
