package nu.hjemme.web.dto;

import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private List<MenuItemDto> menuItems = new ArrayList<>();

    public MenuDto(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            this.menuItems.add(new MenuItemDto(menuItem));
        }
    }

    public List<MenuItemDto> getMenuItems() {
        return menuItems;
    }
}
