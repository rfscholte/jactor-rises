package nu.hjemme.web.menu;

public final class MenuTargetRequest {
    private MenuTargetRequest() { }

    private static ThreadLocal<MenuItemTarget> requestedByThread = new ThreadLocal<>();

    public MenuTargetRequest(MenuItemTarget requestedTarget) {
        requestedByThread.set(requestedTarget);
    }

    public static boolean isRequestFor(MenuItemTarget menuItemTarget) {
        return menuItemTarget != null && menuItemTarget.equals(requestedByThread.get());
    }
}
