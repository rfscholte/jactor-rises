package nu.hjemme.client.domain.menu;

import nu.hjemme.client.datatype.Name;

import java.util.List;

public interface Menu {

    /** @return the name of the menu */
    Name getName();

    /** @return the menu items of this menu */
    List<MenuItem> getMenuItems();
}
