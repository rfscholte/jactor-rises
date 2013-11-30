package nu.hjemme.web.interceptor;

import nu.hjemme.client.datatype.Name;

/**
 * The values used by the interceptors on this web application
 * @author Tor Egil Jacobsen
 */
final class InterceptorValues {
    private InterceptorValues() {
    }

    static final Name MAIN_MENU = new Name("main");
    static final Name PROFILE_MENU = new Name("profile");

    static final String ATTRIBUTE_ACTION = "action";
    static final String ATTRIBUTE_MAIN_ITEMS = "mainItems";
    static final String ATTRIBUTE_PARAMETERS = "parameters";
    static final String ATTRIBUTE_PROFILE_ITEMS = "profileItems";
}
