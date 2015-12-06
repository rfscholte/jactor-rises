package nu.hjemme.web.menu.builder;

import nu.hjemme.business.domain.builder.DomainBuilder;
import nu.hjemme.business.domain.menu.MenuDomain;
import nu.hjemme.business.domain.menu.MenuItemDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder extends DomainBuilder<MenuDomain> {

    private Name nameOfMenu;
    private final List<MenuItem> menuItems = new ArrayList<>();

    @Override protected MenuDomain initDomain() {
        return new MenuDomain(nameOfMenu, menuItems);
    }

    public MenuBuilder with(Name nameOfMenu) {
        this.nameOfMenu = nameOfMenu;
        return this;
    }

    public MenuBuilder add(MenuItemBuilder menuItemBuilder) {
        return add(menuItemBuilder.build());
    }

    public MenuBuilder add(MenuItemDomain menuItemDomain) {
        menuItems.add(menuItemDomain);
        return this;
    }

    public MenuBuilder withName(String name) {
        return with(new Name(name));
    }
}
