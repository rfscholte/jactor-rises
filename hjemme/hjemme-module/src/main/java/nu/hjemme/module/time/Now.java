package nu.hjemme.module.time;

import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class Now {
    private static Now instance;

    static {
        instance = new Now();
    }

    public static LocalDateTime retrieveDateTime() {
        return instance.retrieveDateTimeFromInstance();
    }

    protected LocalDateTime retrieveDateTimeFromInstance() {
        return new LocalDateTime();
    }

    protected static void setInstance(Now instance) {
        Now.instance = instance;
    }
}
