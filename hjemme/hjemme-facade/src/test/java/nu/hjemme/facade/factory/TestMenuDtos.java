package nu.hjemme.facade.factory;

import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.dto.MenuItemDto;

import java.util.List;

import static java.util.Arrays.asList;

/** @author Tor Egil Jacobsen */
class TestMenuDtos {
    private MenuDto menuDto = new MenuDto();

    List<MenuDto> retrieveDtoAsList() {
        return asList(menuDto);
    }

    TestMenuDtos menuName(String menuName) {
        menuDto.setName(menuName);
        return this;
    }

    public TestMenuDtos addItem(String itemName, String target) {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setName(itemName);
        menuItemDto.setMenuItemTarget(target);
        menuDto.getMenuItems().add(menuItemDto);

        return this;
    }
}
