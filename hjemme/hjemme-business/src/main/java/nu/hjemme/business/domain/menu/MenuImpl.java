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
import java.util.stream.Collectors;

/** A {@link MenuImpl} that contains a collection of {@link MenuItemImpl}s. */
public class MenuImpl implements Menu {
    private static MenuImpl newInstanceCreator = new MenuImpl();

    private final Name menuName;
    private final List<MenuItemImpl> menuItems = new ArrayList<>();

    public MenuImpl() {
        this.menuName = new Name("new instance creator");
    }

    public MenuImpl(Menu menu) {
        Validate.notEmpty(menu.getMenuItems(), "There must be provided at least one menu item");
        this.menuName = menu.getName();

        menuItems.addAll(menu.getMenuItems().stream().map(MenuItemImpl::newInstance).collect(Collectors.toList()));
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
    public List<ChosenMenuItem> retrieveChosenMenuItemsBy(MenuItemTarget menuItemTarget) {
        List<ChosenMenuItem> chosenMenuItems = new ArrayList<>(menuItems.size());

        chosenMenuItems.addAll(menuItems.stream().map(menuItem -> new ChosenMenuItemImpl(menuItem, menuItemTarget)).collect(Collectors.toList()));

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

    public static MenuImpl newInstance(Menu menu) {
        return newInstanceCreator.createInstance(menu);
    }

    public static void setNewInstanceCreator(MenuImpl newInstanceCreator) {
        MenuImpl.newInstanceCreator = newInstanceCreator;
    }
}
