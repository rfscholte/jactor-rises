package nu.hjemme.facade.service;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.client.domain.menu.dto.MenuDto;
import nu.hjemme.client.domain.menu.dto.MenuItemDto;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.facade.config.HjemmeAppContext;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.TypeSafeBuildMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;

import static nu.hjemme.test.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeAppContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class}, loader = AnnotationConfigContextLoader.class)
public class MenuFacadeIntegrationTest {
    @Resource
    MenuFacade testMenuFacade;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenFindingMenuItemsAndTheNameIsUnknownTheMethodWillFail() {
        expectedException.expect(IllegalArgumentException.class);
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("some target"), new Name("unknown"));
        testMenuFacade.retrieveChosenMenuItemBy(menuTarget);
    }

    @Test
    public void whenFindingMenuItemsAndTheNameIsKnownTheListOfMenuItemsWillBeReturned() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("bullseye?some=where"), new Name("testMenu"));

        List<ChosenMenuItem> chosenMenuItems = testMenuFacade.retrieveChosenMenuItemBy(menuTarget);

        assertThat(chosenMenuItems, new TypeSafeBuildMatcher<List<ChosenMenuItem>>("En liste med test menyvalg fra test context") {
            @Override
            public MatchBuilder matches(List<ChosenMenuItem> chosenMenuItems, MatchBuilder matchBuilder) {
                matchBuilder.matches(chosenMenuItems.isEmpty(), is(equalTo(false), "lista kan ikke være tom"));

                for (ChosenMenuItem chosenMenuItem : chosenMenuItems) {
                    if (new Name("testParent").equals(chosenMenuItem.getDescription().getItemName())) {
                        matchBuilder.matches(chosenMenuItem.isChildChosen(), is(equalTo(true), "testParent sitt barn skal vaere valgt"));
                    } else {
                        matchBuilder.matches(chosenMenuItem.isChosen(), is(equalTo(true), "menyvalget skal være valgt"));
                    }
                }

                return matchBuilder;
            }
        });
    }

    /**
     * @author Tor Egil Jacobsen
     */
    @Configuration
    public static class HjemmeTestMenus {
        @Bean
        @SuppressWarnings("unused") // brukes av spring
        public MenuDto createTestMenu() {
            return new MenuDto("testMenu")
                    .leggTil(new MenuItemDto("testParent", "bullseye")
                                    .leggTilBarn(new MenuItemDto("testChild", "bullseye?some=where"))
                    );

        }
    }
}
