package nu.hjemme.client.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.List;

/**
 * @author Tor Egil Jacobsen
 */
public interface Menu {

    /**
     * @param menuItemTarget som er ønskelig
     * @return en liste av {@link nu.hjemme.client.domain.menu.ChosenMenuItem}s basert på ønsket {@link nu.hjemme.client.datatype.MenuItemTarget}
     */
    List<ChosenMenuItem> retrieveChosenMenuItemsBy(MenuItemTarget menuItemTarget);
}
