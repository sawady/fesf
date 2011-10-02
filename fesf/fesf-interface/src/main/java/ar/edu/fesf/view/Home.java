package ar.edu.fesf.view;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.PersonProvider;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class Home extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    private transient PersonService personService;

    public Home() {
        super();
        this.initialize();
    }

    public final void initialize() {

        @SuppressWarnings("unchecked")
        IColumn<Person>[] columns = new PropertyColumn[1];

        columns[0] = new PropertyColumn<Person>(new Model<String>("Name"), "name");

        DataTable<Person> dataTable = new DataTable<Person>("personTable", columns, new PersonProvider(
                this.personService), 5);
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable));
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, null));
        this.add(dataTable);

        Button addPersonButton = new Button("addObjectButton") {

            private static final long serialVersionUID = 8828916903354913099L;

            @Override
            public void onSubmit() {

            }
        };

        Form<Object> form = new Form<Object>("form") {

            private static final long serialVersionUID = -4578693870902648117L;

            @Override
            protected void onSubmit() {
                Home.this.personService.addPerson(new Person("Carlos"));
            }
        };

        form.add(addPersonButton);
        this.add(form);

    }
}
