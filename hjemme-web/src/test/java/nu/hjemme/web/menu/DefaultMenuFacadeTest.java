package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static nu.hjemme.web.menu.Menu.aMenu;
import static nu.hjemme.web.menu.MenuItem.aMenuItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class DefaultMenuFacadeTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willThrowExceptionIfProvidedMenusAreNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Menus must be provided");
        new DefaultMenuFacade((Menu[]) null);
    }

    @Test public void willThrowExceptionIfProvidedMenusAreEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Menus must be provided");
        Menu[] menus = new Menu[]{};

        new DefaultMenuFacade(menus);
    }

    @Test public void willFailWhenMenuIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unknown menu");
        expectedException.expectMessage("known.menu");
        expectedException.expectMessage("unknown.menu");

        MenuItemTarget somewhere = new MenuItemTarget("somewhere");

        DefaultMenuFacade defaultMenuFacadeToTest = new DefaultMenuFacade(aMenu().withName("known.menu").add(aMenuItem()).build());
        defaultMenuFacadeToTest.fetchMenuItemBy(new MenuTarget(somewhere, new Name("unknown.menu")));
    }

    @Test public void willFindKnownMenuItems() {
        MenuItemTarget somewhere = new MenuItemTarget("somewhere");

        MenuItem menuItem = aMenuItem().build();
        DefaultMenuFacade defaultMenuFacadeToTest = new DefaultMenuFacade(aMenu().withName("known.menu").add(menuItem).build());

        List<MenuItem> menuItems = defaultMenuFacadeToTest.fetchMenuItemBy(new MenuTarget(somewhere, new Name("known.menu")));

        assertThat(menuItems, hasItem(menuItem));
    }
}
