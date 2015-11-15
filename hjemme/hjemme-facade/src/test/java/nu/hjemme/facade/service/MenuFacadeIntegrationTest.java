package nu.hjemme.facade.service;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.MenuItem;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class, HjemmeDbContext.class})
public class MenuFacadeIntegrationTest {
    @Resource @SuppressWarnings("unused") // initialized by spring
    private MenuFacade testMenuFacade;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void whenFindingMenuItemsAndTheNameIsUnknownTheMethodWillFail() {
        expectedException.expect(IllegalArgumentException.class);
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("some target"), new Name("unknown"));
        testMenuFacade.retrieveChosenMenuItemBy(menuTarget);
    }

    @Test public void whenFindingMenuItemsAndTheNameIsKnownTheListOfMenuItemsWillBeReturned() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("bullseye?some=where"), new Name("testMenu"));

        List<MenuItem> menuItems = testMenuFacade.retrieveChosenMenuItemBy(menuTarget);

        assertThat(menuItems, new TypeSafeBuildMatcher<List<MenuItem>>("En liste med test menyvalg fra test context") {
            @Override public MatchBuilder matches(List<MenuItem> menuItems, MatchBuilder matchBuilder) {
                matchBuilder.matches(menuItems.isEmpty(), is(equalTo(false), "lista kan ikke være tom"));

                for (MenuItem menuItem : menuItems) {
                    if (new Name("testParent").equals(menuItem.getDescription().getItemName())) {
                        matchBuilder.matches(menuItem.isChildChosen(), is(equalTo(true), "testParent sitt barn skal vaere valgt"));
                    } else {
                        matchBuilder.matches(menuItem.isChosen(), is(equalTo(true), "menyvalgget skal være valgt"));
                    }
                }

                return matchBuilder;
            }
        });
    }

    @Configuration
    public static class HjemmeTestMenus {
//        @Bean @SuppressWarnings("unused") // brukes av spring
//        public MenuDto createTestMenu() {
//            return new MenuDto("testMenu")
//                    .add(new MenuItemDto("testParent", "bullseye")
//                            .add(new MenuItemDto("testChild", "bullseye?some=where"))
//                    );
//
//        }
    }
}
