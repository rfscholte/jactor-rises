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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeWebContext.class, HjemmeWebContext.class, HjemmeWebDbContext.class})
public class MenuFacadeIntegrationTest {
    @Resource(name = "hjemme.web.menuFacade") private MenuFacade testMenuFacade;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void whenFindingMenuItemsAndTheNameIsUnknownTheMethodWillFail() {
        expectedException.expect(IllegalArgumentException.class);
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("some target"), new Name("unknown"));
        testMenuFacade.fetchMenuItemBy(new MenuTargetRequest(menuTarget));
    }

    @Test public void whenFindingMenuItemsAndTheNameIsKnownTheListOfMenuItemsWillBeReturned() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("home.do?choose=jactor"), new Name("main"));
        MenuTargetRequest menuTargetRequest = new MenuTargetRequest(menuTarget);

        List<MenuItem> menuItems = testMenuFacade.fetchMenuItemBy(menuTargetRequest);

        for (MenuItem menuItem : menuItems) {
            Name itemName = menuItem.getDescription().getItemName();
            Name chosenName = new Name("menu.main.jactor");

            if (new Name("menu.main.home").equals(itemName)) {
                assertThat(menuItem.isChildChosen()).as("home.children").isEqualTo(true);
            } else if (chosenName.equals(itemName)) {
                assertThat(menuItem.isChosen()).as("menu.main.jactor").isEqualTo(true);
            } else if (!chosenName.equals(itemName)) {
                assertThat(menuItem.isChildChosen()).as("other item names").isEqualTo(false);
            }
        }
    }
}
