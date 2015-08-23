package nu.hjemme.persistence.base;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeConverter implements TypeConverter<LocalDateTime, Date> {
    @Override public LocalDateTime convert(Date from) {
        Date date = from;

        if (from instanceof java.sql.Date) {
            long time = from.getTime();
            date = new Date(time);
        } else if (from == null) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
