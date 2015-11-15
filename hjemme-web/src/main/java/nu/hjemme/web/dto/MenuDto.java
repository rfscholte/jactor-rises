package nu.hjemme.web.dto;

import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private List<MenuItemDto> menuItems = new ArrayList<>();

    public MenuDto(List<MenuItem> menuItems) {
        for (MenuItem chosenMenuItem : menuItems) {
            this.menuItems.add(new MenuItemDto(chosenMenuItem));
        }
    }

    public List<MenuItemDto> getMenuItems() {
        return menuItems;
    }
}
