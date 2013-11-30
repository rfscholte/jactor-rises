package nu.hjemme.client.menu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MenuTest {

    private static final String CHILD_TARGET = "childTarget";
    private static final String PARAM = "param";
    private static final String TEST_CHILD = "testChild";
    private static final String VALUE = "value";

    private Menu menuToTest;

    @Before
    public void setUp() {
        menuToTest = new Menu();
    }

    @Test
    public void willInitWithAnEmptyListOfMenuItems() {
        assertThat("The menu domain should not have an list of items which is null!", menuToTest.getItems(), is(notNullValue()));
        assertThat("The list of items should be empty!", menuToTest.getItems().isEmpty(), is(true));
    }

    @Test
    public void willGetActiveMenuItems() {
        menuToTest.setItems(menus() //
            .addMenuItem(MenuItem.get() //
                .itemChoiceKey("testItem") //
                .targetAsString("testTarget") //
                .addChild(MenuItem.get() //
                    .addParameter(PARAM, VALUE) //
                    .itemChoiceKey(TEST_CHILD) //
                    .targetAsString(CHILD_TARGET) //
                    .instance()) //
                .instance()) //
            .list());

        MenuItemTarget menuItemTarget = new MenuItemTarget(TEST_CHILD, CHILD_TARGET, param(PARAM, VALUE));
        List <MenuItem> items = menuToTest.getActiveMenu(menuItemTarget);

        assertThat("The number of items are not expected!", items.size(), is(equalTo(1)));
        MenuItem item = items.get(0);

        assertThat("The item should not be chosen!", item.isChosen(menuItemTarget), is(equalTo(false)));
        assertThat("The item should be active!", item.isActive(menuItemTarget), is(equalTo(true)));
        assertThat("The item should have a child!", item.getChildren().size(), is(equalTo(1)));
    }

    private Map <String, String> param(String key, String value) {
        Map <String, String> param = new HashMap <String, String>(1);
        param.put(key, value);
        return param;
    }

    @Test
    public void willGetInactiveMenuItem() {
        menuToTest.setItems(menus() //
            .addMenuItem(MenuItem.get() //
                .itemChoiceKey("testItem") //
                .targetAsString("testTarget") //
                .addChild(MenuItem.get() //
                    .addParameter(PARAM, VALUE) //
                    .itemChoiceKey(TEST_CHILD) //
                    .targetAsString(CHILD_TARGET) //
                    .instance()) //
                .instance()) //
            .list());

        MenuItemTarget menuItemTarget = new MenuItemTarget(TEST_CHILD, CHILD_TARGET);
        List <MenuItem> items = menuToTest.getActiveMenu(menuItemTarget);

        assertThat("The number of items are not expected!", items.size(), is(equalTo(1)));
        MenuItem item = items.get(0);

        assertThat("The item should not be chosen!", item.isChosen(menuItemTarget), is(equalTo(false)));
        assertThat("The item should not be active!", item.isActive(menuItemTarget), is(equalTo(false)));
        assertThat("The item should not have children!", item.getChildren().isEmpty(), is(equalTo(true)));
    }

    public MenusBuilder menus() {
        return new MenusBuilder();
    }

    private class MenusBuilder {
        private List <MenuItem> menus = new ArrayList <MenuItem>();

        public List <MenuItem> list() {
            return menus;
        }

        public MenusBuilder addMenuItem(MenuItem menuItem) {
            menus.add(menuItem);
            return this;
        }
    }
}
