package nu.hjemme.module.menu;

import static java.util.Arrays.asList;
import nu.hjemme.client.menu.Menu;
import nu.hjemme.client.menu.MenuItemTarget;
import nu.hjemme.client.menu.MenuName;

import org.junit.Test;

public class MenuServiceTest {

    private MenuBean testMenuService;

    @Test(expected = IllegalArgumentException.class)
    public void willNotInitWhenNotGivenMenus() {
        testMenuService = new MenuBean(null);
    }

    @Test
    public void willInitWhenGivenMenus() {
        testMenuService = new MenuBean(asList(Menu.get().menuName(new MenuName("test")).instance()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void willNotAcceptMenuNameAsNullWhenAskedForAwareMenuItemList() {
        testMenuService = new MenuBean(asList(Menu.get().menuName(new MenuName("test")).instance()));
        testMenuService.getAwareMenuItems(null, new MenuItemTarget("named", "target"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void willNotAcceptMenuItemTargetAsNullWhenAskedForAwareMenuItemList() {
        testMenuService = new MenuBean(asList(Menu.get().menuName(new MenuName("test")).instance()));
        testMenuService.getAwareMenuItems(new MenuName("name"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionIfTheMenuNameIsUnknownWhenAskedForAwareMenuItemList() {
        testMenuService = new MenuBean(asList(Menu.get().menuName(new MenuName("test")).instance()));
        testMenuService.getAwareMenuItems(new MenuName("unknown"), new MenuItemTarget("named", "target"));
    }

    @Test
    public void willGetAwareMenuItemsWhenAsked() {
        testMenuService = new MenuBean(asList(Menu.get().menuName(new MenuName("known")).instance()));
        testMenuService.getAwareMenuItems(new MenuName("known"), new MenuItemTarget("named", "target"));
    }
}
