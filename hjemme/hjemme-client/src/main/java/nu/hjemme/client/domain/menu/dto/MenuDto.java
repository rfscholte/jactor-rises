package nu.hjemme.client.domain.menu.dto;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDto implements Menu {
    private final String name;
    private final List<MenuItemDto> menuItems = new ArrayList<>();

    public MenuDto(String name) {
        this.name = name;
    }

    public MenuDto leggTil(MenuItemDto menuItemDto) {
        menuItems.add(menuItemDto);
        return this;
    }

    @Override
    public List<? extends MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public Name getName() {
        return new Name(name);
    }

    @Override
    public List<ChosenMenuItem> retrieveChosenMenuItemsBy(MenuItemTarget menuItemTarget) {
        throw new UnsupportedOperationException("not implemented in the client");
    }
}
