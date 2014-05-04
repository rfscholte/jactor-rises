package nu.hjemme.facade.factory;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.ChosenMenuItem;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.facade.SpringCtx;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

        assertThat(chosenMenuItems, new NotNullBuildMatching<List<ChosenMenuItem>>("En liste med test menyvalg fra test context") {
            @Override
            public MatchBuilder matches(List<ChosenMenuItem> chosenMenuItems, MatchBuilder matchBuilder) {
                matchBuilder.matches(chosenMenuItems.isEmpty(), is(equalTo(false)), "lista kan ikke være tom");

                for (ChosenMenuItem chosenMenuItem : chosenMenuItems) {
                    if (new Name("testParent").equals(chosenMenuItem.getDescription().getItemName())) {
                        matchBuilder.matches(chosenMenuItem.isChildChosen(), is(equalTo(true)), "testParent sitt barn skal vaere valgt");
                    } else {
                        matchBuilder.matches(chosenMenuItem.isChosen(), is(equalTo(true)), "menyvalget skal være valgt");
                    }
                }

                return matchBuilder;
            }
        });
    }
}
