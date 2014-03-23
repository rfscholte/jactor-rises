package nu.hjemme.facade.factory;

import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.dto.MenuItemDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class MenuFacadeFactoryTest {

    private MenuFacadeFactory testMenuFacadeFactory;

    @Before
    public void initForTesting() {
        testMenuFacadeFactory = new MenuFacadeFactory();
    }

    @Test
    public void willCreateAnInstance() {
        testMenuFacadeFactory.setMenus(
                new TestMenuDtos()
                        .menuName("menuName")
                        .addItem("itemName", "target?param=value")
                        .retrieveDtoAsList()
        );

        assertThat("MenuFacadeFactory", testMenuFacadeFactory.getFacade(), is(notNullValue()));
    }

    private static class TestMenuDtos {
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
}
