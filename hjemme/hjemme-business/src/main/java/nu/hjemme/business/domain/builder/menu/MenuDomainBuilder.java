package nu.hjemme.business.domain.builder.menu;

import nu.hjemme.business.domain.builder.DomainBuilder;
import nu.hjemme.business.domain.menu.MenuDomain;
import nu.hjemme.business.domain.menu.MenuItemDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDomainBuilder extends DomainBuilder<MenuDomain> {

    private Name nameOfMenu;
    private final List<MenuItem> menuItems = new ArrayList<>();

    @Override protected MenuDomain initDomain() {
        return new MenuDomain(nameOfMenu, menuItems);
    }

    public MenuDomainBuilder with(Name nameOfMenu) {
        this.nameOfMenu = nameOfMenu;
        return this;
    }

    public MenuDomainBuilder add(MenuItemDomainBuilder menuItemDomainBuilder) {
        return add(menuItemDomainBuilder.build());
    }

    public MenuDomainBuilder add(MenuItemDomain menuItemDomain) {
        menuItems.add(menuItemDomain);
        return this;
    }

    public MenuDomainBuilder withName(String name) {
        return with(new Name(name));
    }
}
