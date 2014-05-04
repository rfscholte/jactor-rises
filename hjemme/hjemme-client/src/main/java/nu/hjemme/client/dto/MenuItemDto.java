package nu.hjemme.client.dto;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class MenuItemDto {
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

    public MenuItemDto medBeskrivelseSom(String beskrivelse) {
        this.beskrivelse = beskrivelse;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getMenuItemTarget() {
        return menuItemTarget;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public List<MenuItemDto> getChildren() {
        return children;
    }
}
