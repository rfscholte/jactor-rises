package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;

public final class MenuItemRequest {
    private MenuItemRequest() { }

    private static ThreadLocal<MenuItemTarget> requestedByThread = new ThreadLocal<>();

    public MenuItemRequest(MenuItemTarget requestedTarget) {
        requestedByThread.set(requestedTarget);
    }

    public static boolean isRequestFor(MenuItemTarget menuItemTarget) {
        return menuItemTarget != null && menuItemTarget.equals(requestedByThread.get());
    }
}
