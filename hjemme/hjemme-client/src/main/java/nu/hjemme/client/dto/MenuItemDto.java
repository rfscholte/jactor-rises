package nu.hjemme.client.dto;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class MenuItemDto {
    private String name;
    private String menuItemTarget;
    private String description;
    private List<MenuItemDto> children = new ArrayList<MenuItemDto>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuItemTarget() {
        return menuItemTarget;
    }

    public void setMenuItemTarget(String menuItemTarget) {
        this.menuItemTarget = menuItemTarget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItemDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemDto> children) {
        this.children = children;
    }
}
