package nu.hjemme.client.domain.menu;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;

import java.util.List;

public interface MenuItem {

    List<MenuItem> getChildren();

    Description getDescription();

    MenuItemTarget getMenuItemTarget();

    /**
     * @return {@code true} when requested {@link MenuItemTarget} is the same as its items target
     */
    boolean isChosen();

    /**
     * @return {@code true} when requested {@link MenuItemTarget} is the same as its childs target
     */
    boolean isChildChosen();
}
