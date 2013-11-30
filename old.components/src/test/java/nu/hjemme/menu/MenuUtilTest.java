package nu.hjemme.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nu.hjemme.i18n.menu.Menu;
import nu.hjemme.i18n.menu.MenuItem;
import nu.hjemme.i18n.menu.MenuUtil;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A test of the menu utility.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class MenuUtilTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private final Mockery mockery = new JUnit4Mockery();
    private MenuItem menuItemMock = null;
    private Menu menuMock = null;

    @Before
    public void doBefore() {
        menuItemMock = mockery.mock(MenuItem.class);
        menuMock = mockery.mock(Menu.class);
    }

    @Test
    public void testBuildParameterString() {
        final Map <String, String> params = new HashMap <String, String>();
        params.put("TestParam1", "TestValue1");
        params.put("TestParam2", "TestValue2");

        mockery.checking(new Expectations() {
            {
                one(menuItemMock).getParameters();
                will(returnValue(params));
            }
        });

        String paramString = MenuUtil.buildParameterString(menuItemMock);
        assertNotNull("The parameter string should be made!", paramString);
        assertEquals("The parameter string is not right!", "?TestParam1=TestValue1&TestParam2=TestValue2", paramString);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testBuilEmptyParameterString() {
        assertEquals("An empty parameter string should be buildt!", "", MenuUtil.buildParameterString(null));

        mockery.checking(new Expectations() {
            {
                one(menuItemMock).getParameters();
                will(returnValue(null));
            }
        });

        String paramString = MenuUtil.buildParameterString(menuItemMock);
        assertNotNull("The parameter string should be made!", paramString);
        assertEquals("The parameter string should be empty!", "", paramString);
        mockery.assertIsSatisfied();
        final Map <String, String> params = new HashMap <String, String>();

        mockery.checking(new Expectations() {
            {
                one(menuItemMock).getParameters();
                will(returnValue(params));
            }
        });

        paramString = MenuUtil.buildParameterString(menuItemMock);
        assertNotNull("The parameter string should be made!", paramString);
        assertEquals("The parameter string should be empty!", "", paramString);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testMenuToStringWithoutChildren() {
        assertNull("No menu to make a string of!", MenuUtil.toString((Menu) null));
        final List <MenuItem> items = getMenuItems();
        final Map <String, String> params = getTestMap();

        mockery.checking(new Expectations() {
            {
                one(menuMock).getFilepath();
                will(returnValue("/test/menu/test_menu.xml"));
                one(menuMock).getItems();
                will(returnValue(items));
                one(menuMock).getName();
                will(returnValue("TestMenu"));
                one(menuItemMock).getItemChoiceText();
                will(returnValue("Test choice A"));
                one(menuItemMock).getItemChoiceText();
                will(returnValue("Test choice B"));
                one(menuItemMock).getTarget();
                will(returnValue("Target A"));
                one(menuItemMock).getTarget();
                will(returnValue("Target B"));
                one(menuItemMock).getParameters();
                will(returnValue(params));
                one(menuItemMock).getParameters();
                will(returnValue(null));
                one(menuItemMock).getChildren();
                will(returnValue(null));
                one(menuItemMock).getChildren();
                will(returnValue(null));
            }
        });

        String menuString = MenuUtil.toString(menuMock);
        assertNotNull("The menu is converted to a string!", menuString);
        log.debug(menuString);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testMenuItemToString() {
        assertNull("No menu item to make a string of!", MenuUtil.toString((MenuItem) null));
        final List <MenuItem> children = new ArrayList <MenuItem>(1);
        children.add(menuItemMock);

        mockery.checking(new Expectations() {
            {
                one(menuItemMock).getItemChoiceText();
                will(returnValue("Test choice"));
                one(menuItemMock).getTarget();
                will(returnValue("Target"));
                one(menuItemMock).getParameters();
                will(returnValue(getTestMap()));
                one(menuItemMock).getChildren();
                will(returnValue(getMenuItems()));
                one(menuItemMock).getItemChoiceText();
                will(returnValue("Children choice A"));
                one(menuItemMock).getTarget();
                will(returnValue("Children target A"));
                one(menuItemMock).getParameters();
                will(returnValue(null));
                one(menuItemMock).getChildren();
                will(returnValue(null));
                one(menuItemMock).getItemChoiceText();
                will(returnValue("Children choice B"));
                one(menuItemMock).getTarget();
                will(returnValue("Children target B"));
                one(menuItemMock).getParameters();
                will(returnValue(null));
                one(menuItemMock).getChildren();
                will(returnValue(null));
            }
        });

        String menuItemString = MenuUtil.toString(menuItemMock);
        assertNotNull("The menu item is converted to a string!", menuItemString);
        log.debug(menuItemString);
        mockery.assertIsSatisfied();
    }

    private List <MenuItem> getMenuItems() {
        final List <MenuItem> items = new ArrayList <MenuItem>(2);
        items.add(menuItemMock);
        items.add(menuItemMock);
        return items;
    }

    private Map <String, String> getTestMap() {
        final Map <String, String> params = new HashMap <String, String>(2);
        params.put("TestParameterA", "TestValueA");
        params.put("TestParameterB", "TestValueB");
        return params;
    }
}
