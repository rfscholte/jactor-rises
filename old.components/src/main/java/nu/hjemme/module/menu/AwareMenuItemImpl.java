package nu.hjemme.module.menu;

import java.util.ArrayList;
import java.util.List;

import nu.hjemme.client.menu.AwareMenuItem;
import nu.hjemme.client.menu.MenuItem;
import nu.hjemme.client.menu.MenuItemTarget;

/**
 * Implementation of {@link AwareMenuItem}
 * @author Tor Egil Jacobsen
 */
public class AwareMenuItemImpl extends MenuItem implements AwareMenuItem {

    private boolean active;
    private boolean chosen;

    public AwareMenuItemImpl(MenuItem menuItem, MenuItemTarget target) {
        super(menuItem);
        chosen = menuItem.isChosen(target);
        active = menuItem.isActive(target);
    }

    public boolean isActive() {
        return active;
    }

    public boolean isChosen() {
        return chosen;
    }

    /**
     * @return the menu items children as a {@link List}, an empty {@link List} if the item is not <code>active</code>.
     */
    @Override
    public List <MenuItem> getChildren() {
        if (active) {
            return super.getChildren();
        }

        return new ArrayList <MenuItem>();
    }
}
