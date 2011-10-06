package ar.edu.fesf.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.model.Category;
import ar.edu.fesf.services.BookService;

public class Sidebar extends Panel {

    @SpringBean(name = "service.book")
    private BookService bookService;

    private static final long serialVersionUID = -4399991188117990545L;

    public Sidebar(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {

        this.add(new ListView<Category>("links", this.getBookService().findAllCategories()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Category> item) {
                Link<Category> link = new Link<Category>("link", item.getModel()) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                        this.setResponsePage(new Home(new BookSearchResultPanel("contentPanel", Sidebar.this
                                .getBookService().findByCategory(this.getModelObject()))));
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
}
