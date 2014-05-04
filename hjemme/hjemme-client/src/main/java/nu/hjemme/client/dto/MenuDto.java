package nu.hjemme.client.dto;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class MenuDto {
    private final String name;
    private final List<MenuItemDto> menuItems = new ArrayList<>();

    public MenuDto(String name) {
        this.name = name;
    }

    public MenuDto leggTil(MenuItemDto menuItemDto) {
        menuItems.add(menuItemDto);
        return this;
    }

    public String getName() {
        return name;
    }

    public List<MenuItemDto> getMenuItems() {
        return menuItems;
    }
}
