package nu.hjemme.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.i18n.menu.MenuItemMutable;
import nu.hjemme.i18n.menu.MenuItemMutableImpl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A test of the mutable menu item...
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class MenuItemMutableImplTest {

    private I18nContent contentMock = null;
    private Mockery mockery = new JUnit4Mockery();
    private MenuItemMutableImpl mutableMenuItemImplToTest = null;

    @Before
    public void doBefore() {
        mutableMenuItemImplToTest = new MenuItemMutableImpl();
        contentMock = mockery.mock(I18nContent.class);
    }

    @Test
    public void cloneItem() {
        mutableMenuItemImplToTest.setDescription("desc");
        mutableMenuItemImplToTest.setExternal(true);
        MenuItemMutableImpl clone = mutableMenuItemImplToTest.clone();
        assertNull("There should not be children in the clone!", mutableMenuItemImplToTest.getChildren());
        assertEquals("The item is cloned, so it should be equal!", mutableMenuItemImplToTest.isExternal(), clone.isExternal());
        assertEquals("The item is cloned, so it should be equal!", mutableMenuItemImplToTest.getDescription(), clone
            .getDescription());

        List <MenuItemMutable> child = new ArrayList <MenuItemMutable>(1);
        child.add(new MenuItemMutableImpl());
        mutableMenuItemImplToTest.setMutableChildren(child);
        clone = mutableMenuItemImplToTest.clone();
        assertNotNull("There should be children in the clone!", mutableMenuItemImplToTest.getChildren());
        assertEquals("There should be one child in the clone!", 1, clone.getMutableChildren().size());
    }

    @Test
    public void internationalise() {
        MenuItemMutableImpl child = new MenuItemMutableImpl();
        child.setItemChoiceKey("child.key");
        List <MenuItemMutable> children = new ArrayList <MenuItemMutable>(1);
        children.add(child);
        mutableMenuItemImplToTest.setMutableChildren(children);
        assertNull("The item has no text/description!", mutableMenuItemImplToTest.getItemChoiceText());
        assertNull("The item has no text/description!", mutableMenuItemImplToTest.getDescription());
        mutableMenuItemImplToTest.setItemChoiceKey("test.key");
        assertEquals("The key should be used!", "test.key", mutableMenuItemImplToTest.getItemChoiceText());

        mockery.checking(new Expectations() {
            {
                one(contentMock).get("test.menu.test.key");
                will(returnValue("some test choice!"));
                one(contentMock).get("test.menu.test.key.desc");
                will(returnValue("some test description!"));
                one(contentMock).get("test.menu.child.key");
                will(returnValue("some test child choice!"));
                one(contentMock).get("test.menu.child.key.desc");
                will(returnValue("some test child description!"));
            }
        });

        mutableMenuItemImplToTest.internationalise(contentMock, "test.menu");
        assertEquals("Done by internatonalising!", "some test choice!", mutableMenuItemImplToTest.getItemChoiceText());
        assertEquals("Done by internatonalising!", "some test description!", mutableMenuItemImplToTest.getDescription());
    }
}
