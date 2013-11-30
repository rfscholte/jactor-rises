package nu.hjemme.module.domain;

import nu.hjemme.client.dto.MenuItemDto;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** @author Tor Egil Jacobsen */
class MenuItemBuilderForJUnit {
    private String itemName = "test item";
    private String menuItemTarget = "a target";
    private List<MenuItemDto> children = new ArrayList<MenuItemDto>();

    private MenuItemBuilderForJUnit() {
    }

    MenuItemDto mockDto() {
        return mockDto(itemName, menuItemTarget, children);
    }

    private MenuItemDto mockDto(String itemName, String menuItemTarget) {
        return mockDto(itemName, menuItemTarget, new ArrayList<MenuItemDto>());
    }

    MenuItem retrieveInstance() {
        return new MenuItem(mockDto());
    }

    private MenuItemDto mockDto(String itemName, String menuItemTarget, List<MenuItemDto> children) {
        MenuItemDto mockedDto = mock(MenuItemDto.class);
        when(mockedDto.getName()).thenReturn(itemName);
        when(mockedDto.getMenuItemTarget()).thenReturn(menuItemTarget);
        when(mockedDto.getChildren()).thenReturn(children);

        return mockedDto;
    }

    MenuItemBuilderForJUnit itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    MenuItemBuilderForJUnit menuItemTarget(String menuItemTarget) {
        this.menuItemTarget = menuItemTarget;
        return this;
    }

    MenuItemBuilderForJUnit addChild(String itemName, String target) {
        children.add(mockDto(itemName, target));
        return this;
    }

    public static MenuItemBuilderForJUnit build() {
        return new MenuItemBuilderForJUnit();
    }
}
