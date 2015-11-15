package nu.hjemme.client.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.Name;

import java.util.List;

public interface Menu {

    /** @return the name of the menu */
    Name getName();

    /** @return the menu items of this menu */
    List<MenuItem> getMenuItems();

    /**
     * @param menuItemTarget som er ønskelig
     * @return en liste av {@link nu.hjemme.client.domain.menu.MenuItem}s basert på ønsket {@link nu.hjemme.client.datatype.MenuItemTarget}
     */
    List<MenuItem> fetchMenuItemsBy(MenuItemTarget menuItemTarget);
}
