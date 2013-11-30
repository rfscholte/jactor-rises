package nu.hjemme.i18n.menu;

/**
 * A class which sets menu items on the menu.
 * @author Tor Egil Jacobsen
 */
public interface MenuPopulator {

    /**
     * Read the menu.
     */
    void populateMenu();

    /**
     * @return the populatet menu.
     */
    MenuMutable getMenu();

    /**
     * @param menu the menu to populate.
     */
    void setMenu(MenuMutable menu);
}
