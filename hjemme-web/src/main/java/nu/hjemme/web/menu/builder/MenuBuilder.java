package nu.hjemme.web.menu.builder;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.web.menu.Menu;
import nu.hjemme.web.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {

    private Name nameOfMenu;
    private final List<MenuItem> menuItems = new ArrayList<>();

    protected Menu initDomain() {
        return new Menu(nameOfMenu, menuItems);
    }

    public MenuBuilder with(Name nameOfMenu) {
        this.nameOfMenu = nameOfMenu;
        return this;
    }

    public MenuBuilder add(MenuItemBuilder menuItemBuilder) {
        return add(menuItemBuilder.build());
    }

    public MenuBuilder add(MenuItem menuItemDomain) {
        menuItems.add(menuItemDomain);
        return this;
    }

    public MenuBuilder withName(String name) {
        return with(new Name(name));
    }
}
