package nu.hjemme.facade.aop;

import nu.hjemme.business.domain.menu.ChosenMenuItemCache;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.domain.menu.dto.MenuDto;
import nu.hjemme.client.domain.menu.dto.MenuItemDto;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, ChosenMenuItemAspectIntegrationTest.MockedMenuConfiguration.class, HjemmeDbContext.class})
public class ChosenMenuItemAspectIntegrationTest {

    private ChosenMenuItemCache mockedChosenMenuItemCache;

    @Resource @SuppressWarnings("unused") // initialized by spring
    private MenuFacade menuFacade;

    @Before public void byttUtChosenMenuItemCachePaAspect() {
        mockedChosenMenuItemCache = mock(ChosenMenuItemCache.class);
        ChosenMenuItemAspect.cacheMed(mockedChosenMenuItemCache);
    }

    @Test public void skalSjekkeAtMenyBlirCachet() {
        when(mockedChosenMenuItemCache.isCached(any(MenuTarget.class))).thenReturn(false).thenReturn(true);
        MenuTarget somewhereOnMyMenu = new MenuTarget(new MenuItemTarget("somewhere"), new Name("my.menu"));

        menuFacade.retrieveChosenMenuItemBy(somewhereOnMyMenu);
        menuFacade.retrieveChosenMenuItemBy(somewhereOnMyMenu);
        menuFacade.retrieveChosenMenuItemBy(somewhereOnMyMenu);

        verify(mockedChosenMenuItemCache).cache(eq(somewhereOnMyMenu), anyListOf(ChosenMenuItem.class));
        verify(mockedChosenMenuItemCache, times(2)).retrieveBy(somewhereOnMyMenu);
    }

    @Configuration
    public static class MockedMenuConfiguration {
        @Bean
        @SuppressWarnings("unused") // brukes av spring
        public Menu createMockedMenu() {
            return new MenuDto("my.menu").leggTil(new MenuItemDto("menuvalg", "somewhere"));
        }
    }
}
