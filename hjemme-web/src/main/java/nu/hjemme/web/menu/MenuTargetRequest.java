package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;

public final class MenuTargetRequest {
    private static ThreadLocal<MenuItemTarget> requestedByThread = new ThreadLocal<>();
    private Name menuName;

    public MenuTargetRequest(MenuTarget requestedTarget) {
        menuName = requestedTarget.getMenuName();
        requestedByThread.set(requestedTarget.getMenuItemTarget());
    }

    public Name getMenuName() {
        return menuName;
    }

    public static boolean isRequestFor(MenuItemTarget menuItemTarget) {
        return menuItemTarget != null && menuItemTarget.equals(requestedByThread.get());
    }
}
