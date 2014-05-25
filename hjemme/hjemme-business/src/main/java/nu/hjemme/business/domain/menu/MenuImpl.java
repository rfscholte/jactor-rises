package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link MenuImpl} that contains a collection of {@link MenuItemImpl}s.
 * @author Tor Egil Jacobsen
 */
public class MenuImpl implements Menu {
    private final Name menuName;
    private final List<MenuItemImpl> menuItems = new ArrayList<>();

    public MenuImpl(Menu menu) {
        Validate.notEmpty(menu.getMenuItems(), "There must be provided at least one menu item");
        this.menuName = menu.getName();

        for (MenuItem menuItem : menu.getMenuItems()) {
            menuItems.add(new MenuItemImpl(menuItem));
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(menuName)
                .append(menuItems)
                .toString();
    }

    @Override
    public List<ChosenMenuItem> retrieveChosenMenuItemsBy(MenuItemTarget menuItemTarget) {
        List<ChosenMenuItem> chosenMenuItems = new ArrayList<>(menuItems.size());

        for (MenuItemImpl menuItem : menuItems) {
            chosenMenuItems.add(new ChosenMenuItemImpl(menuItem, menuItemTarget));
        }

        return chosenMenuItems;
    }

    @Override
    public Name getName() {
        return menuName;
    }

    @Override
    public List<? extends MenuItem> getMenuItems() {
        return menuItems;
    }
}
