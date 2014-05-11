package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.domain.menu.ChosenMenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple caching mechanism for a list of {@link nu.hjemme.business.domain.menu.ChosenMenuItem} by {@link nu.hjemme.client.datatype.MenuTarget}
 * @author Tor Egil Jacobsen
 */
public class ChosenMenuItemCache {

    private final Map<MenuTarget, List<ChosenMenuItem>> chosenMenuItemCache;

    public ChosenMenuItemCache() {
        chosenMenuItemCache = new HashMap<>();
    }

    public boolean harCacheAv(MenuTarget menuTarget) {
        return chosenMenuItemCache.containsKey(menuTarget);
    }

    public void cache(MenuTarget menuItemTarget, List<ChosenMenuItem> listeAvChosenMenuItem) {
        chosenMenuItemCache.put(menuItemTarget, listeAvChosenMenuItem);
    }

    public List<ChosenMenuItem> hentFor(MenuTarget menuItemTarget) {
        return chosenMenuItemCache.get(menuItemTarget);
    }
}
