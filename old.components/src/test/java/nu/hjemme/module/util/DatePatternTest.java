package nu.hjemme.module.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * A test of the date pattern enum.
 * @author Tor Egil Jacobsen
 */
public class DatePatternTest {

    @Test
    public void testDateFormatting() {
        String expected = getDateToday();

        String actual = DatePattern.DATE.format(new Date());
        assertEquals("The dates should be equal!", expected, actual);

        actual = DatePattern.DATE.format(System.currentTimeMillis());
        assertEquals("The date should be equal!", expected, actual);
    }

    private String getDateToday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        int today = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        StringBuilder dateValue = new StringBuilder(today > 9 ? "" + today : "0" + today);
        dateValue.append(".");
        dateValue.append(month < 10 ? "0" + month : "" + month);
        dateValue.append(".");
        dateValue.append("" + year);

        return dateValue.toString();
    }
}
