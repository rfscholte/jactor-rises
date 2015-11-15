package nu.hjemme.web.dto;

import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDto {
    private final MenuItem menuItem;
    private final List<MenuItemDto> children = new ArrayList<>();

    public MenuItemDto(MenuItem menuItem) {
        this.menuItem = menuItem;

        for (MenuItem chosenChild : menuItem.getChildren()) {
            children.add(new MenuItemDto(chosenChild));
        }
    }

    public boolean isChosen() {
        return menuItem.isChosen();
    }

    public boolean isChildChosen() {
        return menuItem.isChildChosen();
    }

    public List<MenuItemDto> getChildren() {
        return children;
    }
}
