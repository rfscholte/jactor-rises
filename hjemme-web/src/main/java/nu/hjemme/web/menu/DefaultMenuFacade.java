package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.facade.MenuFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notEmpty;

/** The implementation of {@link MenuFacade} */
public class DefaultMenuFacade {
    private Map<Name, Menu> menusByName = new HashMap<>();

    public DefaultMenuFacade(Menu... menus) {
        notEmpty(menus, "Menus must be provided");

        for (Menu menu : menus) {
            Name menuName = menu.getName();
            menusByName.put(menuName, new Menu(menu.getName(), menu.getMenuItems()));
        }
    }

    public List<MenuItem> fetchMenuItemBy(MenuTarget menuTarget) {
        Name name = menuTarget.getMenuName();
        isTrue(menusByName.containsKey(name), name + " is an unknown menu. Known menus: " + menusByName.keySet());

        return menusByName.get(name).getMenuItems();
    }
}
