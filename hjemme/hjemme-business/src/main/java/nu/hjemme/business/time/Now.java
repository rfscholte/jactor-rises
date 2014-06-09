package nu.hjemme.business.time;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class Now {
    private static final Object SYNC = new Object();

    private static volatile Now instance;

    static {
        instance = new Now();
    }

    public static LocalDateTime asDateTime() {
        return instance.nowAsDateTime();
    }

    protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now();
    }

    protected static void setInstance(Now instance) {
        synchronized (SYNC) {
            Now.instance = instance;
        }
    }
}
