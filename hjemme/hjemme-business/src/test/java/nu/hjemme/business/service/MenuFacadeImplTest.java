package nu.hjemme.business.service;

import nu.hjemme.business.domain.menu.MenuImpl;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuFacadeImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    static private MenuImpl mockedMenuImpl;

    @Test
    public void willThrowExceptionIfProvidedMenusAreNull() {
        expectedException.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(null);
    }

    @Test
    public void willThrowExceptionIfProvidedMenusAreEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(Collections.<Menu>emptyList());
    }

    @Test
    public void willFailWhenMenuIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unknown configuration");
        expectedException.expectMessage("known.menu");
        expectedException.expectMessage("unknown.menu");

        MenuItemTarget somewhere = new MenuItemTarget("somewhere");
        Name knownMenu = new Name("known.menu");

        when(mockedMenuImpl.getName()).thenReturn(knownMenu);

        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImpl(asList(mockedMenuImpl));
        testMenuFacadeImpl.retrieveChosenMenuItemBy(
                new MenuTarget(somewhere, new Name("unknown.menu"))
        );
    }

    @Test
    public void willFindKnownMenuItems() {
        MenuItemTarget somewhere = new MenuItemTarget("somewhere");
        Name knownMenu = new Name("known.menu");

        when(mockedMenuImpl.getName()).thenReturn(knownMenu);

        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImpl(asList(mockedMenuImpl));
        testMenuFacadeImpl.retrieveChosenMenuItemBy(new MenuTarget(somewhere, knownMenu));

        verify(mockedMenuImpl).retrieveChosenMenuItemsBy(somewhere);
    }
}
