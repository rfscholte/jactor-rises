package nu.hjemme.persistence.orm.time;

import java.time.LocalDateTime;
import java.util.Date;

public class Now {
    private static final Object SYNC = new Object();

    private static volatile Now instance;

    static {
        instance = new Now();
    }

    protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now();
    }

    protected Date nowAsJavaUtilDate() {
        return new Date();
    }

    public static LocalDateTime asDateTime() {
        return instance.nowAsDateTime();
    }

    public static Date asJavaUtilDate() {
        return instance.nowAsJavaUtilDate();
    }

    protected static void setInstance(Now instance) {
        synchronized (SYNC) {
            Now.instance = instance;
        }
    }
}
