package nu.hjemme.business.service;

import nu.hjemme.business.domain.menu.MenuImpl;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import nu.hjemme.client.service.MenuFacade;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The implementation of {@link MenuFacade} */
public class MenuFacadeImpl implements MenuFacade {
    private Map<Name, Menu> menusByName = new HashMap<>();

    public MenuFacadeImpl(List<Menu> menus) {
        Validate.notEmpty(menus, "Menus must be provided");

        for (Menu menu : menus) {
            Name menuName = menu.getName();
            menusByName.put(menuName, MenuImpl.newInstance(menu));
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<MenuItem> retrieveChosenMenuItemBy(MenuTarget menuTarget) {
        Name name = throwIllegalArgumentExceptionIfUnknown(menuTarget.getMenuName());

        return menusByName.get(name).retrieveChosenMenuItemsBy(menuTarget.getMenuItemTarget());
    }

    private Name throwIllegalArgumentExceptionIfUnknown(Name name) {
        Validate.isTrue(menusByName.containsKey(name), name + " is an unknown configuration. Configured menu names: " + menusByName.keySet());

        return name;
    }
}
