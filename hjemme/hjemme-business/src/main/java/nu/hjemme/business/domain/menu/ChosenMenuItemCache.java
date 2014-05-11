package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple caching mechanism for a list of {@link nu.hjemme.business.domain.menu.ChosenMenuItem} by {@link nu.hjemme.client.datatype.MenuItemTarget}
 * @author Tor Egil Jacobsen
 */
public class ChosenMenuItemCache {

    private final Map<MenuItemTarget, List<ChosenMenuItem>> chosenMenuItemCache;

    public ChosenMenuItemCache() {
        chosenMenuItemCache = new HashMap<>();
    }

    public boolean harCacheAv(MenuItemTarget menuItemTarget) {
        return chosenMenuItemCache.containsKey(menuItemTarget);
    }

    public void cache(MenuItemTarget menuItemTarget, List<ChosenMenuItem> listeAvChosenMenuItem) {
        chosenMenuItemCache.put(menuItemTarget, listeAvChosenMenuItem);
    }

    public List<ChosenMenuItem> hentFor(MenuItemTarget menuItemTarget) {
        return chosenMenuItemCache.get(menuItemTarget);
    }
}
