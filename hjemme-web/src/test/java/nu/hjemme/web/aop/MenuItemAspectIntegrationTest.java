package nu.hjemme.web.aop;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.web.menu.Menu;
import nu.hjemme.web.menu.MenuFacade;
import nu.hjemme.web.menu.MenuItem;
import nu.hjemme.web.menu.MenuItemCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static nu.hjemme.web.menu.Menu.aMenu;
import static nu.hjemme.web.menu.MenuItem.aMenuItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuItemAspectIntegrationTest.MenuConfiguration.class})
public class MenuItemAspectIntegrationTest {

    private MenuItemCache menuItemCacheMock;

    private MenuFacade menuFacade;

    @Before public void byttUtMenuItemCachePaAspect() {
        menuItemCacheMock = mock(MenuItemCache.class);
        MenuItemAspect.cacheMed(menuItemCacheMock);
    }

    @Test public void skalSjekkeAtMenyBlirCachet() {
        when(menuItemCacheMock.isCached(any(MenuTarget.class))).thenReturn(false).thenReturn(true);
        MenuTarget somewhereOnMyMenu = new MenuTarget(new MenuItemTarget("somewhere"), new Name("my.menu"));

        menuFacade.fetchMenuItemBy(somewhereOnMyMenu);
        menuFacade.fetchMenuItemBy(somewhereOnMyMenu);
        menuFacade.fetchMenuItemBy(somewhereOnMyMenu);

        verify(menuItemCacheMock).cache(eq(somewhereOnMyMenu), anyListOf(MenuItem.class));
        verify(menuItemCacheMock, times(2)).fetchBy(somewhereOnMyMenu);
    }

    @Configuration
    public static class MenuConfiguration {
        @Bean public Menu createMenu() {
            return aMenu().withName("my.menu").add(aMenuItem().with(new MenuItemTarget("somewhere"))).build();
        }
    }
}
