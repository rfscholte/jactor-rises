package nu.hjemme.persistence.orm.time;

import org.junit.rules.ExternalResource;

public class NowAsPureDateRule extends ExternalResource {
    @Override protected void before() {
        NowAsPureDate.set();
    }

    @Override protected void after() {
        NowAsPureDate.remove();
    }

    public static NowAsPureDateRule init() {
        return new NowAsPureDateRule();
    }
}
