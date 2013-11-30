package nu.hjemme.module.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Get dates to human readable format.
 * @author Tor Egil Jacobsen
 */
public enum DatePattern {
    DATE("dd.MM.yyyy"), DATE_HOUR("dd.MM.yyyy HH:mm"), DATE_HOUR_SECONDS("dd.MM.yyyy HH:mm:ss"), TIME_UNIT_SECONDS("s.SSS"),
    FILE_TIMESTAMP("yyyyMMddHHmmssSSS"), FILE_DATE("yyyyMMdd"), TIME_UNIT_HOURS("H.mm");

    private SimpleDateFormat dateFormatter = null;

    private DatePattern(final String dateFormat) {
        this.dateFormatter = new SimpleDateFormat(dateFormat, new Locale("no"));
    }

    /**
     * @return a formatted date with java.text.SimpleDateFormat.
     * @param date the date to format.
     */
    public String format(final Date date) {
        return dateFormatter.format(date);
    }

    /**
     * @return a formatted date with java.text.SimpleDateFormat.
     * @param milliseconds the date represented as milliseconds.
     */
    public String format(final Long milliseconds) {
        return dateFormatter.format(milliseconds);
    }
}
