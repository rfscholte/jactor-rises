package nu.hjemme.client.menu;

import java.util.Set;

import nu.hjemme.domain.Menu;

/**
 * The cache of menus.
 * @author Tor Egil Jacobsen
 */
public interface MenusCache {

    /**
     * The ioc name of the menus cache...
     */
    String IOC_NAME = "menusCache";

    /**
     * Returns the menu of choice.<br>
     * It will never return the cached menu, only a clone of the cached menu.
     * @param menuName The name of the menu of choice.
     * @return The menu of choice.
     */
    Menu getMenuClone(String menuName);

    /**
     * Get the names of the menus in the application.
     * @return The names of the menus.
     */
    Set <String> getNames();
}
