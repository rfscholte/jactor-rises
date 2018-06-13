package com.github.jactor.rises.test.extension.time;

import com.github.jactor.rises.commons.time.Now;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class NowAsPureDateExtension extends Now implements BeforeEachCallback, AfterEachCallback {

    private NowAsPureDateExtension() {
    }

    @Override protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    @Override protected Date nowAsDate() {
        return Date.from(nowAsDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override public void beforeEach(ExtensionContext extensionContext) {
        Now.reset(new NowAsPureDateExtension());
    }

    @Override public void afterEach(ExtensionContext extensionContext) {
        Now.reset(new Now());
    }
}
