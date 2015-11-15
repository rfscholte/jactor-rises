package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** A {@link MenuImpl} that contains a collection of {@link MenuItemImpl}s. */
public class MenuImpl implements Menu {
    private static MenuImpl newInstanceCreator = new MenuImpl();

    private final Name menuName;
    private final List<MenuItem> menuItems = new ArrayList<>();

    private MenuImpl() {
        this(new Name("new instance creator"));
    }

    private MenuImpl(Name menuName) {
        this.menuName = menuName;
    }

    public MenuImpl(Menu menu) {
        Validate.notEmpty(menu.getMenuItems(), "There must be provided at least one menu item");
        this.menuName = menu.getName();

//        menuItems.addAll(menu.getMenuItems().stream().map(MenuItemImpl::newInstance).collect(Collectors.toList()));
    }

    protected MenuImpl createInstance(Menu menu) {
        return new MenuImpl(menu);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(menuName)
                .append(menuItems)
                .toString();
    }

    @Override
    public List<MenuItem> retrieveChosenMenuItemsBy(MenuItemTarget menuItemTarget) {
        List<MenuItem> menuItems = new ArrayList<>(this.menuItems.size());

        menuItems.addAll(this.menuItems.stream().map(menuItem -> new MenuItemImpl(menuItem, menuItemTarget)).collect(Collectors.toList()));

        return menuItems;
    }

    @Override
    public Name getName() {
        return menuName;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static MenuImpl newInstance(Menu menu) {
        return newInstanceCreator.createInstance(menu);
    }

    public static void resetNewInstanceCreator() {
        newInstanceCreator = new MenuImpl();
    }
}
