package nu.hjemme.web.menu;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.web.config.HjemmeWebContext;
import nu.hjemme.web.config.HjemmeWebDbContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.build;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeWebContext.class, HjemmeWebContext.class, HjemmeWebDbContext.class})
public class MenuFacadeIntegrationTest {
    @Resource(name = "hjemme.web.menuFacade") private MenuFacade testMenuFacade;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void whenFindingMenuItemsAndTheNameIsUnknownTheMethodWillFail() {
        expectedException.expect(IllegalArgumentException.class);
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("some target"), new Name("unknown"));
        testMenuFacade.fetchMenuItemBy(menuTarget);
    }

    @Test public void whenFindingMenuItemsAndTheNameIsKnownTheListOfMenuItemsWillBeReturned() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("home.do?choose=jactor"), new Name("main"));
        new MenuItemRequest(new MenuItemTarget("home.do?choose=jactor"));

        List<MenuItem> listOfMenuItems = testMenuFacade.fetchMenuItemBy(menuTarget);

        assertThat(listOfMenuItems, build("En liste med menyvalg", (menuItems, matchBuilder) -> {
                    matchBuilder.matches(menuItems, is(not(empty()), "lista kan ikke være tom"));

                    for (MenuItem menuItem : menuItems) {
                        Name itemName = menuItem.getDescription().getItemName();
                        Name chosenName = new Name("menu.main.jactor");

                        if (new Name("menu.main.home").equals(itemName)) {
                            matchBuilder.matches(menuItem.isChildChosen(), is(equalTo(true), "home.children"));
                        } else if (chosenName.equals(itemName)) {
                            matchBuilder.matches(menuItem.isChosen(), is(equalTo(true), "menu.main.jactor"));
                        } else if (!chosenName.equals(itemName)) {
                            matchBuilder.matches(menuItem.isChildChosen(), is(equalTo(false), "other item names"));
                        }
                    }

                    return matchBuilder;
                }
        ));
    }
}
