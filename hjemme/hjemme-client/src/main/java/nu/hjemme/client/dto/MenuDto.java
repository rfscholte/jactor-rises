package nu.hjemme.client.dto;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class MenuDto {
    private String name;
    private List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItemDto> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDto> menuItems) {
        this.menuItems = menuItems;
    }
}
