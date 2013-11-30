package nu.hjemme.facade.factory;

import nu.hjemme.client.MenuFacade;
import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.ChosenMenuItem;
import nu.hjemme.facade.SpringCtx;
import nu.hjemme.test.RequirementsMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        SpringCtx.HJEMME_FACADE_TEST_BEANS
})
public class MenuFacadeFactoryIntegrationTest {

    @Resource
    MenuFacade testMenuFacade;

    @Test(expected = IllegalArgumentException.class)
    public void whenFindingMenuItemsAndTheNameIsUnknownTheMethodWillFail() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("some target"), new Name("unknown"));
        testMenuFacade.retrieveChosenMenuItemBy(menuTarget);
    }

    @Test
    public void whenFindingMenuItemsAndTheNameIsKnownAListOfMenuItemsWillBeReturned() {
        MenuTarget menuTarget = new MenuTarget(new MenuItemTarget("bullseye?some=where"), new Name("testMenu"));

        List<ChosenMenuItem> chosenMenuItems = testMenuFacade.retrieveChosenMenuItemBy(menuTarget);

        assertThat(chosenMenuItems, is(aListWhichContainsCorrectChosenMenuItems()));
    }

    private Matcher<List<ChosenMenuItem>> aListWhichContainsCorrectChosenMenuItems() {
        return new RequirementsMatcher<List<ChosenMenuItem>>("aListWhichContainsCorrectChosenMenuItems") {
            @Override
            protected void checkRequirementsFor(List<ChosenMenuItem> typeSafeItemToMatch) {
                List<ChosenMenuItem> emptyList = new ArrayList<ChosenMenuItem>();
                checkIf("Items", typeSafeItemToMatch, is(not(emptyList)));

                for (ChosenMenuItem chosenMenuItem : typeSafeItemToMatch) {
                    if (new Name("testParent").equals(chosenMenuItem.getDescription().getItemName())) {
                        checkIf("is child chosen", chosenMenuItem.isChildChosen(), is(equalTo(true)));
                    } else {
                        checkIf("is chosen", chosenMenuItem.isChosen(), is(equalTo(true)));
                    }
                }
            }
        };
    }
}
