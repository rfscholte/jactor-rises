package nu.hjemme.client.domain.menu.dto;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDto implements MenuItem {
    private final List<MenuItemDto> children = new ArrayList<>();
    private final String menuItemTarget;
    private final String name;
    private String beskrivelse;

    public MenuItemDto(String name, String menuItemTarget) {
        this.name = name;
        this.menuItemTarget = menuItemTarget;
    }

    public MenuItemDto leggTilBarn(MenuItemDto barn) {
        children.add(barn);
        return this;
    }

    public MenuItemDto withDescriptionAs(String beskrivelse) {
        this.beskrivelse = beskrivelse;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override public Description getDescription() {
        return new Description(name, beskrivelse);
    }

    @Override public MenuItemTarget getMenuItemTarget() {
        return new MenuItemTarget(menuItemTarget);
    }

    @Override public List<? extends MenuItem> getChildren() {
        return children;
    }
}
