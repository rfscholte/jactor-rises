package nu.hjemme.client.facade;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.domain.ChosenMenuItem;

import java.util.List;

/** Finds {@link nu.hjemme.client.domain.MenuItem}s by name represented by a {@link ChosenMenuItem}. */
public interface MenuFacade {

    /**
     * @param menuTarget determine the menu where the menu item target belongs.
     * @return a list of {@link nu.hjemme.client.domain.ChosenMenuItem} according to the request.
     */
    List<ChosenMenuItem> retrieveChosenMenuItemBy(MenuTarget menuTarget);
}
