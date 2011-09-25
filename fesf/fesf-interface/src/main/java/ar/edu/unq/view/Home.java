package ar.edu.unq.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

public class Home extends WebPage {

    // @SpringBean(name = "service.person")
    // PersonService personService;

    public Home() {
        super();
        this.initialize();
    }

    public final void initialize() {
        Label markupLabel = new Label("labelExample", "sarasa");
        markupLabel.setEscapeModelStrings(false);
        this.add(markupLabel);

        Button addPersonButton = new Button("addObjectButton") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                this.info("addPersonButton.onSubmit executed");
            }
        };

        Form<Object> form = new Form<Object>("form") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                this.info("Form.onSubmit pre-executed");
                // personService.addPerson(new Person());
                this.info("Form.onSubmit post-executed");
                this.setResponsePage(Home.class);
            }
        };

        form.add(addPersonButton);
        this.add(form);

    }
}
