package nu.hjemme.web.dto;

import nu.hjemme.client.datatype.MenuItemTarget;

import javax.servlet.http.HttpServletRequest;

/**
 * A menu item target for an web application which will remove the context root of the web application from the requested target.
 */
public class MenuItemTargetDto extends MenuItemTarget {

    private static final String CONTEXT_ROOT = "/hjemme/";

    public MenuItemTargetDto(HttpServletRequest httpServletRequest) {
        super(removeContextRoot(httpServletRequest.getRequestURI()));
    }

    private static String removeContextRoot(String target) {
        return target.replace(CONTEXT_ROOT, "");
    }
}
