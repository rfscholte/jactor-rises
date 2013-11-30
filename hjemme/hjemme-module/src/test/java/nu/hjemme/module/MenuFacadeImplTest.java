package nu.hjemme.module;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.ChosenMenuItem;
import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.dto.MenuItemDto;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuFacadeImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void willThrowExceptionIfProvidedMenusAreNull() {
        thrown.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(null);
    }

    @Test
    public void willThrowExceptionIfProvidedMenusAreEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new MenuFacadeImpl(Collections.<MenuDto>emptyList());
    }

    @Test
    public void willFailWhenMenuIsUnknown() {
        thrown.expect(IllegalArgumentException.class);

        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImplForJUnit()
                .setMenuName("known menu")
                .initWithOneMenuAndOneMenuItem();

        testMenuFacadeImpl.retrieveChosenMenuItemBy(
                new MenuTarget(new MenuItemTarget("anywhere"), new Name("unknown menu"))
        );
    }

    @Test
    public void willFindKnownMenuItems() {
        MenuFacadeImpl testMenuFacadeImpl = new MenuFacadeImplForJUnit()
                .setMenuName("known menu")
                .initWithOneMenuAndOneMenuItem();

        List<ChosenMenuItem> items = testMenuFacadeImpl.retrieveChosenMenuItemBy(
                new MenuTarget(new MenuItemTarget("somewhere"), new Name("known menu"))
        );

        assertThat("MenuItems", items, is(notNullValue()));
        assertThat("MenuItems", items, is(not(Collections.<ChosenMenuItem>emptyList())));
    }

    private class MenuFacadeImplForJUnit {
        private String menuName;

        MenuFacadeImplForJUnit setMenuName(String menuName) {
            this.menuName = menuName;
            return this;
        }

        MenuFacadeImpl initWithOneMenuAndOneMenuItem() {
            MenuItemDto mockedMenuItemDto = mock(MenuItemDto.class);
            when(mockedMenuItemDto.getMenuItemTarget()).thenReturn("somewhere");
            when(mockedMenuItemDto.getName()).thenReturn("testItem");

            MenuDto mockedMenuDto = mock(MenuDto.class);
            when(mockedMenuDto.getName()).thenReturn(menuName);
            when(mockedMenuDto.getMenuItems()).thenReturn(asList(mockedMenuItemDto));

            return new MenuFacadeImpl(asList(mockedMenuDto));
        }
    }
}
