package nu.hjemme.i18n.menu;

import java.util.List;
import java.util.Locale;


/**
 * A mutable menu.
 * @author Tor Egil Jacobsen
 */
public interface MenuMutable extends Menu {

    /**
     * @param items the items to set.
     */
    void setItems(List <MenuItemMutable> items);

    /**
     * @param lastModified milliseconds for the last time the menu was modified.
     */
    void setLastModified(Long lastModified);

    /**
     * @param name the name of the menu.
     */
    void setName(String name);

    /**
     * @param menuFolder the folder where the menu configuration is present.
     */
    void setMenuFolder(String menuFolder);

    /**
     * @param fileName the file name for the menu.
     */
    void setFileName(String fileName);

    /**
     * I18n this menu.
     * @param locale the <code>java.util.Locale</code> which determine the international value.
     */
    void internationalise(Locale locale);
}
