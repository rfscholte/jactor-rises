package nu.hjemme.web.controller;

public final class SpringCtx {

    private SpringCtx() {
        // to avoid initializing...
    }

    /** The spring classpath for the application context... */
    public static final String HJEMME_APPLICATION = "classpath:hjemme.web-app.xml";
}
