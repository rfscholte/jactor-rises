package nu.hjemme.web.aop;

import nu.hjemme.business.domain.menu.MenuItemCache;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.MenuItem;
import nu.hjemme.client.facade.MenuFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static nu.hjemme.business.domain.menu.MenuDomain.aMenuDomain;
import static nu.hjemme.business.domain.menu.MenuItemDomain.aMenuItemDomain;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuItemAspectIntegrationTest.MenuConfiguration.class, HjemmeDbContext.class})
public class MenuItemAspectIntegrationTest {

    private MenuItemCache menuItemCacheMock;

    @Resource @SuppressWarnings("unused") // initialized by spring
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
            return aMenuDomain().withName("my.menu").add(aMenuItemDomain().with(new MenuItemTarget("somewhere"))).build();
        }
    }
}
