package nu.hjemme.client.facade;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.List;

/** Finds {@link nu.hjemme.client.domain.menu.MenuItem}s by {@link MenuTarget} */
public interface MenuFacade {

    /**
     * @param menuTarget determine the menu where the menu item target belongs.
     * @return a list of {@link nu.hjemme.client.domain.menu.MenuItem} according to the request.
     */
    List<MenuItem> fetchMenuItemBy(MenuTarget menuTarget);
}
