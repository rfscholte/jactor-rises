package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.MenuItemTarget;

/** @author Tor Egil Jacobsen */
public interface PickChosenMenuItem {

    boolean isChosenBy(MenuItemTarget menuItemTarget);

    boolean isChildChosenBy(MenuItemTarget menuItemTarget);
}
