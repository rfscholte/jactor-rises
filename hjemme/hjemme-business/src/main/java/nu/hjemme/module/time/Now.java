package nu.hjemme.module.time;

import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class Now {
    private static Now instance;

    static {
        instance = new Now();
    }

    public static LocalDateTime retrieveDateTime() {
        return instance.retrieveTheDateTime();
    }

    protected LocalDateTime retrieveTheDateTime() {
        return new LocalDateTime();
    }

    protected static void setInstance(Now instance) {
        Now.instance = instance;
    }
}
