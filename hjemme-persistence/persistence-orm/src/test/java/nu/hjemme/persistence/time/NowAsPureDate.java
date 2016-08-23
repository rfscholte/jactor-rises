package nu.hjemme.persistence.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class NowAsPureDate extends Now {

    public NowAsPureDate() {
        setInstance(this);
    }

    @Override
    protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    @Override protected Date nowAsJavaUtilDate() {
        return Date.from(nowAsDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void removeNowAsPureDate() {
        setInstance(new Now());
    }
}
