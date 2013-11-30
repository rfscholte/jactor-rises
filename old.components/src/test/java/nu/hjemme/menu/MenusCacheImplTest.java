package nu.hjemme.menu;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import nu.hjemme.api.menu.MenusCache;
import nu.hjemme.i18n.menu.Menu;
import nu.hjemme.i18n.menu.MenuMutable;
import nu.hjemme.i18n.menu.MenuPopulator;
import nu.hjemme.i18n.menu.MenusCacheImpl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of the menus cache.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class MenusCacheImplTest {

    private MenuPopulator menuPopulatorMock;
    private MenusCache menusCacheImplToTest;
    private Mockery mockery = new JUnit4Mockery();
    private MenuMutable menuMock;

    @Before
    public void doBefore() {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
        menuMock = mockery.mock(MenuMutable.class);
        menuPopulatorMock = mockery.mock(MenuPopulator.class);
        menusCacheImplToTest = new MenusCacheImpl();
        // menusCacheImplToTest.setMenuPopulator(menuPopulatorMock);
    }

    @Test
    public void verifyNameList() {
        Set <String> names = menusCacheImplToTest.getNames();
        assertNull("There are no menus (to be) cached!", names);
        setMenu("menuMock");
        names = menusCacheImplToTest.getNames();
        assertTrue("The mocked menu is set to be cached!", names.contains("menuMock"));
    }

    @Test(expected = IllegalStateException.class)
    public void illegalState() {
        menusCacheImplToTest.getMenuClone("menuMock");
    }

    @Test
    public void getCloneOfCachedMenu() {
        setMenu("menuMock");
        Menu menu = menusCacheImplToTest.getMenuClone("notCachedMenu");
        assertNull("The menu is not set to be cached!", menu);

        mockery.checking(new Expectations() {
            {
                one(menuMock).clone();
                will(returnValue(menuMock));
                one(menuMock).getFilepath();
                will(returnValue("test.path"));
                one(menuMock).getLastModified();
                will(returnValue(new Long(0)));
            }
        });

        menu = menusCacheImplToTest.getMenuClone("menuMock");
        assertNotNull("A cloned menu should be returned!", menu);
    }

    @Test
    public void populateMenuWhenLastModifiedIsNotAsExpected() {
        setMenu("menuMock");

        mockery.checking(new Expectations() {
            {
                one(menuMock).clone();
                will(returnValue(menuMock));
                one(menuMock).getFilepath();
                will(returnValue("test.path"));
                one(menuMock).getLastModified();
                will(returnValue(new Long(10)));
                one(menuMock).getName();
                will(returnValue("menuMock"));
                one(menuPopulatorMock).setMenu(with(menuMock));
                one(menuPopulatorMock).populateMenu();
            }
        });

        Menu menu = menusCacheImplToTest.getMenuClone("menuMock");
        assertNotNull("A cloned menu should be returned!", menu);
    }

    private void setMenu(final String menuName) {
        mockery.checking(new Expectations() {
            {
                one(menuMock).getName();
                will(returnValue(menuName));
                one(menuPopulatorMock).setMenu(with(menuMock));
                one(menuPopulatorMock).populateMenu();
            }
        });

        Set <MenuMutable> menus = new HashSet <MenuMutable>(1);
        menus.add(menuMock);
        // menusCacheImplToTest.setMenus(menus);
    }
}
