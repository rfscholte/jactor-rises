package nu.hjemme.business.service;

import nu.hjemme.business.domain.menu.MenuDomain;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuFacadeImplTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();
    @Mock static private MenuDomain menuDomainMock;

    private MenuFacadeImpl testMenuFacadeImpl;

    @Before public void initMenuFacadeToTest() {
        testMenuFacadeImpl = new MenuFacadeImpl(singletonList(menuDomainMock));
    }

    @Test public void willThrowExceptionIfProvidedMenusAreNull() {
        expectedException.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(null);
    }

    @Test public void willThrowExceptionIfProvidedMenusAreEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(Collections.<Menu>emptyList());
    }

    @Test public void willFailWhenMenuIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unknown configuration");
        expectedException.expectMessage("known.menu");
        expectedException.expectMessage("unknown.menu");

        MenuItemTarget somewhere = new MenuItemTarget("somewhere");
        Name knownMenu = new Name("known.menu");

        when(menuDomainMock.getName()).thenReturn(knownMenu);

        testMenuFacadeImpl.fetchMenuItemBy(
                new MenuTarget(somewhere, new Name("unknown.menu"))
        );
    }

    @Test public void willFindKnownMenuItems() {
        MenuItemTarget somewhere = new MenuItemTarget("somewhere");
        Name knownMenu = new Name("known.menu");

        when(menuDomainMock.getName()).thenReturn(knownMenu);

        testMenuFacadeImpl.fetchMenuItemBy(new MenuTarget(somewhere, knownMenu));

        verify(menuDomainMock).fetchMenuItemsBy(somewhere);
    }
}
