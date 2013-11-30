package nu.hjemme.module.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nu.hjemme.client.menu.AwareMenuItem;
import nu.hjemme.client.menu.Menu;
import nu.hjemme.client.menu.MenuItem;
import nu.hjemme.client.menu.MenuItemTarget;

import org.apache.commons.lang.Validate;

/**
 * A bean which have a list of {@link AwareMenuItems} by {@link MenuItemTarget} for each {@link Menu}.
 * @author Tor Egil Jacobsen
 */
public class AwareMenuItems {

    private Map <MenuItemTarget, List <AwareMenuItem>> activeItemsByTarget = new HashMap <MenuItemTarget, List <AwareMenuItem>>();
    private Map <MenuItemTarget, List <AwareMenuItem>> inactiveItemsByTarget = new HashMap <MenuItemTarget, List <AwareMenuItem>>();
    private Menu menu;

    /**
     * Creates {@link AwareMenuItems} with the menu it should be aware of.
     * @param menu
     */
    public AwareMenuItems(Menu menu) {
        Validate.notNull(menu, "The AwareMenuItems cannot be aware when no menu given!");
        this.menu = menu;
    }

    /**
     * @param menuItemTarget
     * @return a list of {@link AwareMenuItem} which is aware according to the {@link MenuItemTarget} asked for.
     */
    public List <AwareMenuItem> get(MenuItemTarget menuItemTarget) {
        if (activeItemsByTarget.containsKey(menuItemTarget)) {
            return activeItemsByTarget.get(menuItemTarget);
        }

        if (inactiveItemsByTarget.containsKey(menuItemTarget)) {
            return inactiveItemsByTarget.get(menuItemTarget);
        }

        return createAwareMenuItems(menuItemTarget);
    }

    private List <AwareMenuItem> createAwareMenuItems(MenuItemTarget menuItemTarget) {
        List <AwareMenuItem> awareMenuItems = new ArrayList <AwareMenuItem>(menu.getItems().size());

        boolean active = false;

        for (MenuItem menuItem : menu.getItems()) {
            AwareMenuItemImpl awareItem = new AwareMenuItemImpl(menuItem, menuItemTarget);
            active = awareItem.isActive();
            awareMenuItems.add(awareItem);
        }

        return cacheItems(awareMenuItems, menuItemTarget, active);
    }

    private List <AwareMenuItem> cacheItems(List <AwareMenuItem> awareMenuItems, MenuItemTarget menuItemTarget, boolean active) {
        if (active) {
            activeItemsByTarget.put(menuItemTarget, awareMenuItems);
        } else {
            inactiveItemsByTarget.put(menuItemTarget, awareMenuItems);
        }

        return awareMenuItems;
    }
}
