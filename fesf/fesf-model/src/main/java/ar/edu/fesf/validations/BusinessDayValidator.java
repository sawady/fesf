package ar.edu.fesf.validations;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class BusinessDayValidator {

    private static List<DateTime> nonBusinessDays = new ArrayList<DateTime>();

    private BusinessDayValidator() {

    }

    public static void setNonBusinessDays(final List<DateTime> listOfNonBusinessDays) {
        BusinessDayValidator.nonBusinessDays = listOfNonBusinessDays;
    }

    public static boolean isNonBusinessDay(final DateTime date) {
        if (date.getYear() == new DateTime().getYear()) {
            for (DateTime nbday : nonBusinessDays) {
                if (nbday.getDayOfYear() == date.getDayOfYear()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void validate(final DateTime date) {
        if (BusinessDayValidator.isNonBusinessDay(date)) {
            throw new UserException("The date is not a business day");
        }
    }

    public static DateTime nonBusinessDateAfterNDays(final DateTime date, final int days) {
        DateTime result = date.plusDays(days);

        while (BusinessDayValidator.isNonBusinessDay(result)) {
            result.plusDays(1);
        }

        return result;
    }
}
