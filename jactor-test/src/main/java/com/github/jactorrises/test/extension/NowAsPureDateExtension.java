package com.github.jactorrises.test.extension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.github.jactorrises.client.time.Noq;

public final class NowAsPureDateExtension extends Now {

    private NowAsPureDate() {
    }

    @Override protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    @Override protected Date nowAsDate() {
        return Date.from(nowAsDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void set() {
        reset(new NowAsPureDate());
    }

    public static void remove() {
        reset(new Now());
    }
}
