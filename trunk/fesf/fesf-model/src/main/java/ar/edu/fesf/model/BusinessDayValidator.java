package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class BusinessDayValidator {

    /**
     * TODO, deberian ademas agregar info sobre en concepto de que es feriado
     * (una descripcion)
     */
    private List<DateTime> nonBusinessDays = new ArrayList<DateTime>();

    public void setNonBusinessDays(final List<DateTime> listOfNonBusinessDays) {
        nonBusinessDays = listOfNonBusinessDays;
    }

    public boolean isNonBusinessDay(final DateTime date) {
        for (DateTime nbday : nonBusinessDays) {
            if (nbday.getDayOfYear() == date.getDayOfYear()) {
                return true;
            }
        }
        return false;
    }

    public void validate(final DateTime date) {
        checkArgument(!this.isNonBusinessDay(date), "The date is not a business day");
    }

    public DateTime nonBusinessDateAfterNDays(final DateTime date, final int days) {
        DateTime result = date.plusDays(days);

        while (this.isNonBusinessDay(result)) {
            result = result.plusDays(1);
        }

        return result;
    }
}
