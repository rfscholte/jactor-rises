package nu.hjemme.business.domain.builder.menu;

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
public class MenuItemDomainBuilder extends DomainBuilder<MenuItemDomain> {
    private final List<MenuItem> children = new ArrayList<>();

    private Description description;
    private MenuItemTarget menuItemTarget;

    @Override protected MenuItemDomain initDomain() {
        return new MenuItemDomain(description, menuItemTarget).appendChildren(children);
    }

    public MenuItemDomainBuilder with(MenuItemTarget menuItemTarget) {
        this.menuItemTarget = menuItemTarget;
        return this;
    }

    public MenuItemDomainBuilder add(MenuItemDomainBuilder childBuilder) {
        return add(childBuilder.build());
    }

    public MenuItemDomainBuilder add(MenuItemDomain child) {
        children.add(child);
        return this;
    }

    public MenuItemDomainBuilder withTarget(String menuItemTarget) {
        return with(new MenuItemTarget(menuItemTarget));
    }

    public MenuItemDomainBuilder with(Description description) {
        this.description = description;
        return this;
    }
}
