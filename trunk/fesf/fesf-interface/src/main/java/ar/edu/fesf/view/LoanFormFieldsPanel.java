package ar.edu.fesf.view;

import java.util.Locale;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.convert.IConverter;
import org.joda.time.DateTime;

public class LoanFormFieldsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LoanFormFieldsPanel(final String id) {
        super(id);
        this.initialize();
    }

    private void initialize() {
        RequiredTextField<DateTime> requiredTextField = new RequiredTextField<DateTime>("agreedReturnDate") {
            private static final long serialVersionUID = 1L;

            @Override
            public IConverter getConverter(final Class<?> type) {
                return new IConverter() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Object convertToObject(final String value, final Locale locale) {
                        return new DateTime().plusDays(20);
                    }

                    @Override
                    public String convertToString(final Object value, final Locale locale) {
                        return ((DateTime) value).toString("dd/mm/yyyy", locale);

                    }

                };
            }
        };

        requiredTextField.setType(DateTime.class);
        this.add(requiredTextField);
    }
}
