package nu.hjemme.web.menu.builder;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.web.menu.MenuItem;
import nu.hjemme.web.menu.MenuItemTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * a builder of menu items
 */
public class MenuItemBuilder {
    private final List<MenuItem> children = new ArrayList<>();

    private Name itemName;
    private String description;
    private MenuItemTarget menuItemTarget;

    public MenuItemBuilder add(MenuItemBuilder childBuilder) {
        return add(childBuilder.build());
    }

    public MenuItem build() {
        Description description = new Description(itemName, this.description);
        return new MenuItem(description, menuItemTarget).appendChildren(children);
    }

    public MenuItemBuilder add(MenuItem child) {
        children.add(child);
        return this;
    }

    public MenuItemBuilder withTarget(String menuItemTarget) {
        this.menuItemTarget = new MenuItemTarget(menuItemTarget);
        return this;
    }

    public MenuItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuItemBuilder withName(String name) {
        itemName = new Name(name);
        return this;
    }
}
