package nu.hjemme.business.service;

import nu.hjemme.business.domain.menu.MenuDomain;
import nu.hjemme.business.domain.menu.MenuItemDomain;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static nu.hjemme.business.domain.menu.MenuItemDomain.aMenuItemDomain;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class DefaultMenuFacadeTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willThrowExceptionIfProvidedMenusAreNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Menus must be provided");
        new DefaultMenuFacade(null);
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
        Name knownMenu = new Name("known.menu");

        DefaultMenuFacade defaultMenuFacadeToTest = new DefaultMenuFacade(MenuDomain.aMenuDomain().with(knownMenu).add(aMenuItemDomain()).build());
        defaultMenuFacadeToTest.fetchMenuItemBy(new MenuTarget(somewhere, new Name("unknown.menu")));
    }

    @Test public void willFindKnownMenuItems() {
        MenuItemTarget somewhere = new MenuItemTarget("somewhere");
        Name knownMenu = new Name("known.menu");

        MenuItemDomain menuItemDomain = aMenuItemDomain().build();
        DefaultMenuFacade defaultMenuFacadeToTest = new DefaultMenuFacade(MenuDomain.aMenuDomain().with(knownMenu).add(menuItemDomain).build());

        List<MenuItem> menuItems = defaultMenuFacadeToTest.fetchMenuItemBy(new MenuTarget(somewhere, knownMenu));

        assertThat(menuItems, is(hasItem(menuItemDomain), "menuItems"));
    }
}
