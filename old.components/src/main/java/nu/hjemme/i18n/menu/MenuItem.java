package nu.hjemme.i18n.menu;

import java.util.List;
import java.util.Map;

/**
 * A menu item from the menu (a choice in the menu).<br>
 * <br>
 * The menu item also contains a description. <br>
 * <br>
 * The menus with items is to be found in the files named *menu.xml.
 * @author Tor Egil Jacobsen
 */
public interface MenuItem extends Cloneable {

    /**
     * @return The description of this menu item.
     */
    String getDescription();

    /**
     * @return The text of this menu item choice.
     */
    String getItemChoiceText();

    /**
     * @return <code>true</code> if there is an external target on this menu item.
     */
    boolean isExternal();

    /**
     * @return The target of this menu item.
     */
    String getTarget();

    /**
     * @return The parameters for the target.
     */
    Map <String, String> getParameters();

    /**
     * @return The children of this menu item (
     */
    List <MenuItem> getChildren();
}
