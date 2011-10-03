package ar.edu.fesf.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.version.undo.Change;

import ar.edu.fesf.model.Book;

public class BasePage extends WebPage {

    private Book selected;

    /**
     * Constructor
     */
    public BasePage() {
        super();
        this.add(new Label("selectedLabel", new PropertyModel(this, "selectedBookLabel")));
        this.add(new FeedbackPanel("feedback"));
    }

    /**
     * @return string representation of selected contact property
     */
    public String getSelectedBookLabel() {
        if (this.selected == null) {
            return "No Contact Selected";
        } else {
            return this.selected.getTitle();
        }
    }

    /**
     * 
     */
    class ActionPanel extends Panel {
        private static final long serialVersionUID = -5037214701376347264L;

        /**
         * @param id
         *            component id
         * @param model
         *            model for contact
         */
        public ActionPanel(final String id, final IModel<Book> model) {
            super(id, model);
            BasePage.this.add(new Link("select") {
                @Override
                public void onClick() {
                    BasePage.this.selected = (Book) BasePage.this.getParent().getDefaultModelObject();
                }
            });
        }
    }

    /**
     * @return selected contact
     */
    public Book getSelected() {
        return this.selected;
    }

    /**
     * sets selected contact
     * 
     * @param selected
     */
    public void setSelected(final Book selected) {
        this.addStateChange(new Change() {
            private static final long serialVersionUID = 7339989223779114321L;

            private final Book old = BasePage.this.selected;

            @Override
            public void undo() {
                BasePage.this.selected = this.old;
            }
        });
        this.selected = selected;
    }
}
