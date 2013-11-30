package nu.hjemme.client.menu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {

    private static final String VALUE = "value";
    private static final String SOME = "some";
    private static final String CHILD_DO = "child.do";
    private static final String TEST_DO = "test.do";
    private static final String TEST_ITEM = "testItem";

    private static final MenuItemTarget CHILD_TARGET = new MenuItemTarget(TEST_ITEM, CHILD_DO);
    private static final MenuItemTarget NO_TEST_TARGET = new MenuItemTarget(TEST_ITEM, "not.do");
    private static final MenuItemTarget TEST_DO_TARGET = new MenuItemTarget(TEST_ITEM, TEST_DO);
    private static final MenuItemTarget TEST_DO_TARGET_PARAMS = new MenuItemTarget(TEST_ITEM, TEST_DO, parameter(SOME, VALUE));

    private MenuItem menuItemToTest;

    @Before
    public void setUp() {
        menuItemToTest = new MenuItem();
    }

    @Test
    public void willInitWithAnEmptyChildrenList() {
        assertThat("The list of children should not be null!", menuItemToTest.getChildren(), is(notNullValue()));
        assertThat("There should be an empty list of children!", menuItemToTest.getChildren().isEmpty(), is(true));
    }

    @Test
    public void willTellIfTheMenuItemIsChosen() {
        assertThat("The menu should not be chosen if null is provided!", menuItemToTest.isChosen(null), is(equalTo(false)));
        menuItemToTest.setTargetAsString(TEST_DO);
        menuItemToTest.setItemChoiceKey(TEST_ITEM);
        assertThat("The menu item should not be chosen!", menuItemToTest.isChosen(NO_TEST_TARGET), is(equalTo(false)));
        assertThat("The menu item should be chosen!", menuItemToTest.isChosen(TEST_DO_TARGET), is(equalTo(true)));
    }

    @Test
    public void willTellIfTheMenuItemIsActive() {
        assertThat("The menu item should not be active if null is provided!", menuItemToTest.isActive(null), //
            is(equalTo(false)));

        menuItemToTest.setItemChoiceKey(TEST_ITEM);
        menuItemToTest.setTargetAsString(TEST_DO);
        assertThat("The menu item should not be active if it is not chosen!", menuItemToTest.isActive(NO_TEST_TARGET),
            is(equalTo(false)));

        assertThat("The menu item should be active if it is chosen!", menuItemToTest.isActive(TEST_DO_TARGET),
            is(equalTo(true)));

        assertThat("The menu item should not be active if it is not chosen and has no children!",
            menuItemToTest.isActive(CHILD_TARGET), is(equalTo(false)));

        menuItemToTest.addChildItem(MenuItem.get() //
            .itemChoiceKey(TEST_ITEM) //
            .targetAsString(CHILD_DO) //
            .instance());

        assertThat("The menu item should be active if it is not chosen but one of its children is chosen!",
            menuItemToTest.isActive(CHILD_TARGET), is(equalTo(true)));
    }

    @Test
    public void willUseParametersWhenAskedForChosen() {
        MenuItem menuItem = MenuItem.get() //
            .targetAsString(TEST_DO) //
            .addParameter(SOME, VALUE) //
            .itemChoiceKey(TEST_ITEM) //
            .instance();

        assertThat("Only choice name and target with no parameters are not chosen!", //
            menuItem.isChosen(TEST_DO_TARGET), //
            is(equalTo(false)));
        assertThat("Only choice name and target with no parameters are not chosen!", //
            menuItem.isChosen(TEST_DO_TARGET_PARAMS), //
            is(equalTo(true)));
    }

    private static Map <String, String> parameter(String parameter, String value) {
        Map <String, String> map = new HashMap <String, String>();
        map.put(parameter, value);
        return map;
    }
}
