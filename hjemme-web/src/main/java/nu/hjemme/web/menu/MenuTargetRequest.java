package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;

public final class MenuTargetRequest {
    private static final ThreadLocal<MenuItemTarget> REQUESTED_BY_THREAD = new ThreadLocal<>();
    private final Name menuName;

    public MenuTargetRequest(MenuTarget requestedTarget) {
        menuName = requestedTarget.getMenuName();
        REQUESTED_BY_THREAD.set(requestedTarget.getMenuItemTarget());
    }

    public Name getMenuName() {
        return menuName;
    }

    public static boolean isRequestFor(MenuItemTarget menuItemTarget) {
        return menuItemTarget != null && menuItemTarget.equals(REQUESTED_BY_THREAD.get());
    }
}
