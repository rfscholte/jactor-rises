package nu.hjemme.web.menu.builder;

import nu.hjemme.business.domain.builder.DomainBuilder;
import nu.hjemme.business.domain.menu.MenuItemDomain;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * a builder of menu items
 */
public class MenuItemBuilder extends DomainBuilder<MenuItemDomain> {
    private final List<MenuItem> children = new ArrayList<>();

    private Description description;
    private MenuItemTarget menuItemTarget;

    @Override protected MenuItemDomain initDomain() {
        return new MenuItemDomain(description, menuItemTarget).appendChildren(children);
    }

    public MenuItemBuilder with(MenuItemTarget menuItemTarget) {
        this.menuItemTarget = menuItemTarget;
        return this;
    }

    public MenuItemBuilder add(MenuItemBuilder childBuilder) {
        return add(childBuilder.build());
    }

    public MenuItemBuilder add(MenuItemDomain child) {
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
