package nu.hjemme.client.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.List;

/** @author Tor Egil Jacobsen */
public interface MenuItem {

    List<? extends MenuItem> getChildren();

    Description getDescription();

    MenuItemTarget getMenuItemTarget();
}
