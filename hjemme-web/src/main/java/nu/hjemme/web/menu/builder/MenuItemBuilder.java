package nu.hjemme.web.menu.builder;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.web.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * a builder of menu items
 */
public class MenuItemBuilder {
    private final List<MenuItem> children = new ArrayList<>();

    private Description description;
    private MenuItemTarget menuItemTarget;

    public MenuItemBuilder with(MenuItemTarget menuItemTarget) {
        this.menuItemTarget = menuItemTarget;
        return this;
    }

    public MenuItemBuilder add(MenuItemBuilder childBuilder) {
        return add(childBuilder.build());
    }

    public MenuItem build() {
        return new MenuItem(description, menuItemTarget).appendChildren(children);
    }

    public MenuItemBuilder add(MenuItem child) {
        children.add(child);
        return this;
    }

    public MenuItemBuilder withTarget(String menuItemTarget) {
        return with(new MenuItemTarget(menuItemTarget));
    }

    public MenuItemBuilder with(Description description) {
        this.description = description;
        return this;
    }
}
