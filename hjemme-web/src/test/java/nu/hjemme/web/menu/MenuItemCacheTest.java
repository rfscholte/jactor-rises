package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.build;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MenuItemCacheTest {
    private MenuItemCache testMenuItemCache;
    private MenuTarget somewhereOnTheMenu;
    private MenuTarget somewhereElseOnTheMenu;

    @Before public void initForTest() {
        testMenuItemCache = new MenuItemCache();
    }

    @Before public void initSomewhereOnTheMenu() {
        somewhereOnTheMenu = new MenuTarget(new MenuItemTarget("somewhere"), new Name("main.menu"));
    }

    @Before public void initSomewhereElseOnTheMenu() {
        somewhereElseOnTheMenu = new MenuTarget(new MenuItemTarget("somewhere else"), new Name("main.menu"));
    }

    @Test public void sholdTellIfChosenItemIsCached() {
        assertThat(testMenuItemCache.isCached(somewhereOnTheMenu), is(equalTo(false), "chosen item cached"));
    }

    @Test public void sholdTellIfNotChosenItemIsCached() {
        testMenuItemCache.cache(somewhereOnTheMenu, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(somewhereElseOnTheMenu), is(equalTo(false), "not chosen menu cached"));
    }

    @Test public void shouldCacheChosenMenuItem() {
        testMenuItemCache.cache(somewhereOnTheMenu, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(somewhereOnTheMenu), is(equalTo(true), "chosen from menu"));
    }

    @Test public void shouldCacheMenuItemsBasedOnMenuTarget() {
        @SuppressWarnings("unchecked") final List<MenuItem> eiListeAvMenuItems = mock(List.class);
        @SuppressWarnings("unchecked") final List<MenuItem> eiAnnenListeAvMenuItems = mock(List.class);

        testMenuItemCache.cache(somewhereOnTheMenu, eiListeAvMenuItems);
        testMenuItemCache.cache(somewhereElseOnTheMenu, eiAnnenListeAvMenuItems);

        assertThat(testMenuItemCache, build("MenuItems", (menuItemCache, matchBuilder) -> matchBuilder
                .matches(menuItemCache.fetchBy(somewhereOnTheMenu), is(equalTo(eiListeAvMenuItems), "Cache provided by " + somewhereOnTheMenu))
                .matches(menuItemCache.fetchBy(somewhereElseOnTheMenu), is(equalTo(eiAnnenListeAvMenuItems), "Cache provided by " + somewhereElseOnTheMenu))

        ));
    }
}
