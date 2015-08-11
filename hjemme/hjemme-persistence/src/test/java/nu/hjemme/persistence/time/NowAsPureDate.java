package nu.hjemme.persistence.time;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class NowAsPureDate extends Now {

    public NowAsPureDate() {
        setInstance(this);
    }

    @Override
    protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static void removeNowAsPureDate() {
        setInstance(new Now());
    }
}
