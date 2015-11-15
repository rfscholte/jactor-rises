package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple caching mechanism for a list of {@link MenuItem} by {@link nu.hjemme.client.datatype.MenuTarget}
 */
public class ChosenMenuItemCache {

    private final Map<MenuTarget, List<MenuItem>> chosenMenuItemCache;

    public ChosenMenuItemCache() {
        chosenMenuItemCache = new HashMap<>();
    }

    public boolean isCached(MenuTarget menuTarget) {
        return chosenMenuItemCache.containsKey(menuTarget);
    }

    public void cache(MenuTarget menuItemTarget, List<MenuItem> listeOfMenuItems) {
        chosenMenuItemCache.put(menuItemTarget, listeOfMenuItems);
    }

    public List<MenuItem> retrieveBy(MenuTarget menuItemTarget) {
        return chosenMenuItemCache.get(menuItemTarget);
    }
}
