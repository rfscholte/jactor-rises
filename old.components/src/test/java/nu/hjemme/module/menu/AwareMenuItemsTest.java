package nu.hjemme.module.menu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import nu.hjemme.client.menu.AwareMenuItem;
import nu.hjemme.client.menu.Menu;
import nu.hjemme.client.menu.MenuItem;
import nu.hjemme.client.menu.MenuItemTarget;
import nu.hjemme.client.menu.MenuName;

import org.junit.Test;

public class AwareMenuItemsTest {

    private AwareMenuItems testAwareMenuItems;

    @Test(expected = IllegalArgumentException.class)
    public void willNotBeCreatedWithNullMenu() {
        testAwareMenuItems = new AwareMenuItems(null);
    }

    @Test
    public void willGetInactiveMenuItems() {
        testAwareMenuItems = new AwareMenuItems(Menu.get() //
            .menuName(new MenuName("test")) //
            .addMenuItem(MenuItem.get() //
                .targetAsString("anywhere") //
                .itemChoiceKey("right") //
                .instance()) //
            .instance());

        List <AwareMenuItem> awares = testAwareMenuItems.get(new MenuItemTarget("wrong", "anywhere"));

        assertThat("There should be an aware menu item!", awares.size(), is(equalTo(1)));
        assertThat("The aware menu item should not be active!", awares.get(0).isActive(), is(equalTo(false)));

        List <AwareMenuItem> others = testAwareMenuItems.get(new MenuItemTarget("wrong", "anywhere"));

        assertThat("The items should be cached!", awares == others, is(equalTo(true)));
    }

    @Test
    public void willGetActiveMenuItems() {
        testAwareMenuItems = new AwareMenuItems(Menu.get() //
            .menuName(new MenuName("test")) //
            .addMenuItem(MenuItem.get() //
                .targetAsString("anywhere") //
                .itemChoiceKey("right") //
                .instance()) //
            .instance());

        List <AwareMenuItem> awares = testAwareMenuItems.get(new MenuItemTarget("right", "anywhere"));

        assertThat("There should be an aware menu item!", awares.size(), is(equalTo(1)));
        assertThat("The aware menu item should be active!", awares.get(0).isActive(), is(equalTo(true)));

        List <AwareMenuItem> others = testAwareMenuItems.get(new MenuItemTarget("right", "anywhere"));

        assertThat("The items should be cached!", awares == others, is(equalTo(true)));
    }
}
