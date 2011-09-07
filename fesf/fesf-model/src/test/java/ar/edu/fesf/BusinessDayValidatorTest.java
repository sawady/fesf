package ar.edu.fesf;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.fesf.model.BusinessDayValidator;

public class BusinessDayValidatorTest {

    @Test
    public void isNonBusinessDayWhenNoListOfNonBusinessDays() {
        BusinessDayValidator bs = new BusinessDayValidator();
        assertFalse("The date must be non business", bs.isNonBusinessDay(new DateTime()));
    }

    @Test
    public void isNonBusinessDayWhenNotInTheList() {
        BusinessDayValidator bs = new BusinessDayValidator();

        List<DateTime> list = new ArrayList<DateTime>();

        list.add(new DateTime().plusDays(1));
        list.add(new DateTime().plusDays(2));
        list.add(new DateTime().plusDays(3));

        bs.setNonBusinessDays(list);

        assertFalse("The date must be non business", bs.isNonBusinessDay(new DateTime()));
    }

    @Test
    public void isNonBusinessDayWhenInTheList() {
        BusinessDayValidator bs = new BusinessDayValidator();

        List<DateTime> list = new ArrayList<DateTime>();

        DateTime example = new DateTime("10102010");
        list.add(example);
        list.add(new DateTime().plusDays(2));
        list.add(new DateTime().plusDays(3));

        bs.setNonBusinessDays(list);

        assertTrue("The date must be business", bs.isNonBusinessDay(example));
    }

    @Test
    public void nonBusinessDateAfterNDaysWhenDontAffect() {

        BusinessDayValidator bs = new BusinessDayValidator();

        List<DateTime> list = new ArrayList<DateTime>();

        DateTime example = new DateTime("10102010");
        list.add(example);
        list.add(new DateTime().plusDays(2));
        list.add(new DateTime().plusDays(3));

        bs.setNonBusinessDays(list);

        DateTime expected = example.plusDays(8);
        DateTime actual = bs.nonBusinessDateAfterNDays(example, 8);

        assertEquals("The two dates must be equals", expected, actual);
    }

    @Test
    public void nonBusinessDateAfterNDaysWhenAffect() {

        BusinessDayValidator bs = new BusinessDayValidator();

        List<DateTime> list = new ArrayList<DateTime>();

        DateTime example = new DateTime("10102010");
        list.add(example.plusDays(1));
        list.add(example.plusDays(2));
        list.add(example.plusDays(3));
        list.add(example.plusDays(4));

        bs.setNonBusinessDays(list);

        DateTime expected = example.plusDays(5);
        DateTime actual = bs.nonBusinessDateAfterNDays(example, 1);

        assertEquals("The two dates must be equals", expected, actual);
    }

}
