package nu.hjemme.persistence.time;

import org.junit.rules.ExternalResource;

public class NowAsPureDateRule extends ExternalResource {
    @Override protected void before() {
        new NowAsPureDate();
    }

    @Override protected void after() {
        NowAsPureDate.removeNowAsPureDate();
    }

    public static NowAsPureDateRule init() {
        return new NowAsPureDateRule();
    }
}
