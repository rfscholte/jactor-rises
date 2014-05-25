package nu.hjemme.business.service;

import nu.hjemme.business.domain.menu.MenuItemImpl;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuFacadeImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

        Menu mockedMenu = mock(Menu.class);
        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImpl(asList(mockedMenu));
        MenuItemImpl mockedMenuItem = mock(MenuItemImpl.class);

        when(mockedMenu.getMenuItems()).thenAnswer(somListe(mockedMenuItem));
        when(mockedMenu.getName()).thenReturn(new Name("known menu"));
        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("somewhere"));

        testMenuFacadeImpl.retrieveChosenMenuItemBy(
                new MenuTarget(new MenuItemTarget("anywhere"), new Name("unknown menu"))
        );
    }

    @Test
    public void willFindKnownMenuItems() {
        Menu mockedMenu = mock(Menu.class);
        MenuItemImpl mockedMenuItem = mock(MenuItemImpl.class);

        when(mockedMenu.getMenuItems()).thenAnswer(somListe(mockedMenuItem));
        when(mockedMenu.getName()).thenReturn(new Name("known menu"));
        when(mockedMenuItem.getMenuItemTarget()).thenReturn(new MenuItemTarget("somewhere"));

        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImpl(asList(mockedMenu));

        List<ChosenMenuItem> items = testMenuFacadeImpl.retrieveChosenMenuItemBy(
                new MenuTarget(new MenuItemTarget("somewhere"), new Name("known menu"))
        );

        assertThat(items, new NotNullBuildMatching<List<ChosenMenuItem>>("a list of chosen menu items") {
            @Override
            public MatchBuilder matches(List<ChosenMenuItem> chosenItems, MatchBuilder matchBuilder) {
                matchBuilder.failIfMismatch(chosenItems.size(), is(equalTo(1)), "size of chosen items");

                return matchBuilder.matches(chosenItems.iterator().next().getMenuItemTarget(), is(equalTo(new MenuItemTarget("somewhere"))), "chosen items menu item target");
            }
        });
    }

    private Answer<List<? extends MenuItem>> somListe(MenuItemImpl mockedMenuItem) {
        return invocation -> new ArrayList<>(asList(mockedMenuItem));
    }
}
