package nu.hjemme.i18n.menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nu.hjemme.api.menu.MenusCache;
import nu.hjemme.i18n.menu.MenuMutable;
import nu.hjemme.i18n.menu.MenuPopulator;

import org.apache.log4j.Logger;

/**
 * The cached menus.
 * @author Tor Egil Jacobsen
 */
public class MenusCacheImpl implements MenusCache {

    protected transient final Logger logger = Logger.getLogger(getClass());

    private MenuPopulator menuPopulator = null;
    private Map <String, MenuMutable> menusByName = null;

    /**
     * {@inheritDoc}
     */
    public Menu getMenuClone(String menuName) {
        ensureLatestCache(menuName);

        return cloneMenu(menuName);
    }

    /**
     * {@inheritDoc}
     */
    public Set <String> getNames() {
        return menusByName != null ? menusByName.keySet() : null;
    }

    private Menu cloneMenu(String menuName) {
        MenuMutable menu = menusByName != null ? menusByName.get(menuName) : null;

        return menu != null ? menu.clone() : null;
    }

    private void ensureLatestCache(String menuName) {
        if (menusByName == null) {
            throw new IllegalStateException("The menus cache is not instantiated!");
        } else if (!menusByName.containsKey(menuName)) {
            logger.warn("The cache does not contain a menu named '" + menuName + "'!");
            logger.warn("Contains " + menusByName.keySet());

            return;
        }

        MenuMutable menu = menusByName.get(menuName);
        Long fileLastModified = new File(menu.getFilepath()).lastModified();

        if (!menu.getLastModified().equals(fileLastModified)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Repopulating " + menuName);
            }

            populateMenu(menu);
        }
    }

    private void populateMenu(MenuMutable menu) {
        menuPopulator.setMenu(menu);
        menuPopulator.populateMenu();
        menusByName.put(menu.getName(), menu);
    }

    public void setMenuPopulator(MenuPopulator menuPopulator) {
        this.menuPopulator = menuPopulator;
    }

    public void setMenus(Set <MenuMutable> menus) {
        menusByName = new HashMap <String, MenuMutable>(menus.size());

        for (MenuMutable menu : menus) {
            populateMenu(menu);
        }
    }
}
