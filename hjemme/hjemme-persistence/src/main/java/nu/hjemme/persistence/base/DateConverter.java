package nu.hjemme.persistence.base;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter implements TypeConverter<Date, LocalDateTime> {
    @Override public Date convert(LocalDateTime from) {
        return from != null ? Date.from(from.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }
}
