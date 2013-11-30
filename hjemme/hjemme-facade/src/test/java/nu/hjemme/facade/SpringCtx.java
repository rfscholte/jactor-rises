package nu.hjemme.facade;

public final class SpringCtx {

    private SpringCtx() {
        // to avoid initializing...
    }

    public static final String HJEMME_FACADE_TEST_BEANS = "classpath:hjemme.facade-test.beans.xml";
}
