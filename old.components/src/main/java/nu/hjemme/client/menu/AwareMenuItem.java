package nu.hjemme.client.menu;

import java.util.List;

/**
 * A menu item which is aware of its state when displayed.
 * @author Tor Egil Jacobsen
 */
public interface AwareMenuItem {

    List <MenuItem> getChildren();

    MenuItemTarget getItemTarget();

    String getItemChoiceKey();

    boolean isActive();

    boolean isChosen();

    boolean isExternal();
}
