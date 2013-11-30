package nu.hjemme.facade.menu;

import static nu.hjemme.client.menu.MenuFacade.MAIN_MENU;
import static nu.hjemme.client.menu.MenuFacade.PROFILE_MENU;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nu.hjemme.client.menu.AwareMenuItem;
import nu.hjemme.client.menu.MenuFacade;
import nu.hjemme.client.menu.MenuItemTarget;
import nu.hjemme.facade.SpringCtx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    SpringCtx.HJEMME_ENVIRONEMNT, SpringCtx.HJEMME_MENUS, SpringCtx.HJEMME_MODULES
})
public class MenuFacadeIntegrationTest {

    private static final String NOT_DO = "not.do";
    private static final String NO_ITEM = "no.item";

    @Autowired
    private MenuFacade menuFacadeToTest;

    @Test
    public void willCheckSpringCtx() {
        assertThat("The menu facade should not be null!", menuFacadeToTest, is(notNullValue()));
    }

    @Test
    public void willGetProfileAwareMenuItems() {
        List <AwareMenuItem> profileItems = menuFacadeToTest.getActiveMenuItems(PROFILE_MENU, new MenuItemTarget(NO_ITEM, NOT_DO));

        assertThat("The profile items should not be null!", profileItems, is(notNullValue()));
        assertThat("The number of items should be correct!", profileItems.size(), is(equalTo(3)));
        assertThat("There should not be any children on this item!", profileItems.get(0).getChildren().size(), is(equalTo(0)));

        MenuItemTarget targetAbout = new MenuItemTarget("profile.about", "about.do");
        profileItems = menuFacadeToTest.getActiveMenuItems(PROFILE_MENU, targetAbout);

        assertThat("The profile items should not be null!", profileItems, is(notNullValue()));
        assertThat("The number of items should be correct!", profileItems.size(), is(equalTo(3)));

        AwareMenuItem menuItem = profileItems.get(2);
        assertThat("The menu item with the key '" + menuItem.getItemChoiceKey() + "' should be active!", menuItem.isActive(),
            is(equalTo(true)));
    }

    @Test
    public void willGetActiveMainMenuItems() {
        List <AwareMenuItem> mainItems = menuFacadeToTest.getActiveMenuItems(MAIN_MENU, new MenuItemTarget(NO_ITEM, NOT_DO));

        assertThat("The main items should not be null!", mainItems, is(notNullValue()));
        assertThat("The number of items should be correct!", mainItems.size(), is(equalTo(1)));
        assertThat("There should not be any children on this item!", mainItems.get(0).getChildren().size(), is(equalTo(0)));

        MenuItemTarget targetJactor = new MenuItemTarget("user.show", "user.do", paramter("user", "jactor"));
        mainItems = menuFacadeToTest.getActiveMenuItems(MAIN_MENU, targetJactor);

        assertThat("The main items should not be null!", mainItems, is(notNullValue()));
        assertThat("The number of items should be correct!", mainItems.size(), is(equalTo(1)));

        AwareMenuItem menuItem = mainItems.get(0);
        assertThat("The item choice key is wrong", menuItem.getItemChoiceKey(), is(equalTo("user.choose")));
        assertThat("Two children are expected for '" + menuItem.getItemChoiceKey() + "'!", menuItem.getChildren().size(),
            is(equalTo(2)));
    }

    private Map <String, String> paramter(String parameter, String value) {
        Map <String, String> param = new HashMap <String, String>(1);
        param.put(parameter, value);
        return param;
    }
}
