package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

class MenuItemCacheTest {
    private MenuItemCache testMenuItemCache;
    private MenuTarget somewhereOnTheMenu;
    private MenuTarget somewhereElseOnTheMenu;

    @BeforeEach void initForTest() {
        testMenuItemCache = new MenuItemCache();
    }

    @BeforeEach void initSomewhereOnTheMenu() {
        somewhereOnTheMenu = new MenuTarget(new MenuItemTarget("somewhere"), new Name("main.menu"));
    }

    @BeforeEach void initSomewhereElseOnTheMenu() {
        somewhereElseOnTheMenu = new MenuTarget(new MenuItemTarget("somewhere else"), new Name("main.menu"));
    }

    @Test void sholdTellIfChosenItemIsCached() {
        assertThat(testMenuItemCache.isCached(somewhereOnTheMenu), equalTo(false));
    }

    @Test void sholdTellIfNotChosenItemIsCached() {
        testMenuItemCache.cache(somewhereOnTheMenu, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(somewhereElseOnTheMenu), equalTo(false));
    }

    @Test void shouldCacheChosenMenuItem() {
        testMenuItemCache.cache(somewhereOnTheMenu, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(somewhereOnTheMenu), equalTo(true));
    }

    @Test void shouldCacheMenuItemsBasedOnMenuTarget() {
        @SuppressWarnings("unchecked") final List<MenuItem> eiListeAvMenuItems = mock(List.class);
        @SuppressWarnings("unchecked") final List<MenuItem> eiAnnenListeAvMenuItems = mock(List.class);

        testMenuItemCache.cache(somewhereOnTheMenu, eiListeAvMenuItems);
        testMenuItemCache.cache(somewhereElseOnTheMenu, eiAnnenListeAvMenuItems);

        assertAll("MenuItems",
                () -> MatcherAssert.assertThat("Cache provided by " + somewhereOnTheMenu, testMenuItemCache.fetchBy(somewhereOnTheMenu), equalTo(eiListeAvMenuItems)),
                () -> MatcherAssert.assertThat("Cache provided by " + somewhereElseOnTheMenu, testMenuItemCache.fetchBy(somewhereElseOnTheMenu), equalTo(eiAnnenListeAvMenuItems))
        );
    }
}
