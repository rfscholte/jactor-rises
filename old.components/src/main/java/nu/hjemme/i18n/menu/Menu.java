package nu.hjemme.i18n.menu;

import java.util.List;

/**
 * The menu (which also is a i18n component.
 * @author Tor Egil Jacobsen
 */
public interface Menu extends Cloneable {

    /**
     * @return The path to the menu file.
     */
    String getFilepath();

    /**
     * @return The name of the menu
     */
    String getName();

    /**
     * @return the items to get.
     */
    List <MenuItem> getItems();

    /**
     * @return a clone of this menu.
     */
    Menu clone();

    /**
     * @return The last time this menu was modified, <code>null</code> if unknown.
     */
    Long getLastModified();

    /**
     * @param lastModified the lastModified to set.
     */
    void setLastModified(Long lastModified);
}
